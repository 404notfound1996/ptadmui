package com.goshine.ptadmui.sys.service.impl;

import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;

import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.sys.entity.OrgUser;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.mapper.OrgUserMapper;
import com.goshine.ptadmui.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Organization;
import com.goshine.ptadmui.sys.mapper.OrganizationMapper;
import com.goshine.ptadmui.sys.mapper.UserMapper;
import com.goshine.ptadmui.sys.service.OrganizationService;
import com.goshine.ptadmui.sys.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 组织机构SERVICE实现
 * @author goshine
 */

@Service("orgService")
@Transactional
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService{
    @Autowired
    private OrganizationMapper orgMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
	private OrgUserMapper orgUserMapper;
    @Autowired
	private RoleMapper roleMapper;
	@Override
	protected BaseMapper<Organization> getMapper() {
		return orgMapper;
	}
    
	/**
	 * 新增组织机构
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Organization model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setOrgCode(model.getId());
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	if(StringHandler.isNullOrEmpty(model.getParentId())){
        		model.setParentId("0");
        	}
        	return orgMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新组织机构
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Organization model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return orgMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除组织机构
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	
    	for(String orgId:idList){
    		Organization queryModel=new Organization();
    		queryModel.setParentId(orgId);
    		List<Organization> orgList=orgMapper.queryListByCond(queryModel);
    		if(orgList!=null&&orgList.size()>0){
    			throw new AdmuiException("该组织机构下尚存在子组织机构，暂不允许删除，请先删除子机构后再进行删除操作！");
    		}
    		//判断该机构下是否存在用户信息
	    	UserVo user=new UserVo();
	    	List<String> orgIdList=new ArrayList<String>();
	    	orgIdList.add(orgId);
	    	user.setOrgIds(orgIdList);
	    	List<UserVo> userList=userMapper.queryListByCond(user);
	    	if(userList!=null&&userList.size()>0){
	    		throw new AdmuiException("该机构下尚存在用户记录，不允许删除，请先删除对应用户后再进行删除操作！");
	    	}
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	params.put("modifyUser",context.getUserId());
    	params.put("modifyDate",context.getCurrentDateTime());
    	return orgMapper.deleteByParams(params);
    }
    /**
     * 根据ID查询组织机构信息
     * @param id
     * @return
     */
    @Override
    public Organization queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return orgMapper.queryById(id);
    }
    
    /**
     * 根据条件查询组织机构信息列表
     * @param model
     * @return
     */
    @Override
    public List<Organization> queryListByCond(Context context,Organization model){
    	if(model==null){
    		model=new Organization();
    	}
    	return orgMapper.queryListByCond(model);
    }
    
    /**
     * 分页查询组织机构信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Organization> queryListByPage(Context context,Page<Organization> page) throws AdmuiException{
    	if(page.getData()==null||StringHandler.isNullOrEmpty(page.getData().get("parentId"))||"0".equals(page.getData().get("parentId"))){
    		if(page.getData()==null){
    			Map<String,Object> params=new HashMap<String,Object>();
    			params.put("parentId","0");
    			page.setData(params);
    		}else{
    			page.getData().put("parentId","0");
    		}
    	}
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_organization.org_code desc");
		List<Organization> pageList=orgMapper.queryListByPage(page);
		PageInfo<Organization> pageInfo = new PageInfo<Organization>(pageList);
		return page.generatePage(page,pageInfo);
    }

	@Override
	public Page<UserVo> queryListByOrgID(Context context,Page<UserVo> page) {
		page.getData();
		String value = null;
		for (String key : page.getData().keySet()) {
			value = (String) page.getData().get(key);
		}
		//查出String字符串格式的orgId集合
		String childIdListByOrgId = orgMapper.getChildIdListByOrgId(value);
		String[] strArray = childIdListByOrgId.split(",");
		List<String> strsToList1 = Arrays.asList(strArray);
		List<UserVo> objects = new ArrayList<>();
		//利用TreeSet有序且不重复特性去重
//		HashSet hashSet = new HashSet(strsToList1);
//		strsToList1.clear();
//		strsToList1.addAll(hashSet);
		List<String> idList = strsToList1.stream().distinct().collect(Collectors.toList());

        UserVo userVo = new UserVo();
		userVo.setOrgIds(idList);
		//获取部门下的所有人工
		List<UserVo> userVoList = userMapper.queryListByCond(userVo);
        int begin = (page.getPageIndex()-1)*page.getPageSize();
        int end =begin+page.getPageSize();
        //防止下标越界异常
        if(end>userVoList.size()){
        	end = userVoList.size();
		}
		if(begin>userVoList.size()){
			begin = userVoList.size();
		}
		objects.addAll(userVoList.subList(begin,end));
		//增加字段orgName和roleName
		if (objects != null && objects.size() > 0)
			for (UserVo uservo : objects) {
				uservo.setSexName(Enums.SexType.getName(uservo.getSex()));
				List<OrgUser> orgList = orgUserMapper.queryOrgListByUserId(uservo.getId());
				if (orgList != null && orgList.size() > 0) {
					String orgNames = "";//逗号分隔的结构名称
					for (OrgUser org : orgList) {
						if (orgNames.length() > 0) {
							orgNames += ",";
						}
						orgNames += org.getOrgName();
					}
					uservo.setOrgNames(StringHandler.isNullOrEmpty(orgNames) ? "" : orgNames);
				}
				List<Role> roleList = roleMapper.queryRoleListByUserId(Enums.RoleType.FunctionRole.getIndex(), uservo.getId());
				if (roleList != null && roleList.size() > 0) {
					String roleNames = "";
					for (Role role : roleList) {
						if (roleNames.length() > 0) {
							roleNames += ",";
						}
						roleNames += role.getRoleName();
					}
					uservo.setRoleNames(StringHandler.isNullOrEmpty(roleNames) ? "" : roleNames);
				}
			}
		int total = idList.size();//总条数
		int totalpage = (int) Math.ceil((double) total/(double) page.getPageSize());//总页面数
		page.setTotal(total);
		page.setTotalPage(totalpage);
		page.setPageList(objects);
		return page;
	}

	/**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Organization model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getOrgName())){
    		throw new AdmuiException("机构名称不能为空！");
    	}
    	if(StringHandler.empty(model.getOrgName()).length()>60){
    		throw new AdmuiException("机构名称长度不能超过60字符！");
    	}
    	return true;
    }
}

