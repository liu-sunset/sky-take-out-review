package sky.project.service;

import entity.Address;

import java.util.List;

public interface AddressService {
    //查找用户的所有地址
    public List<Address> selectAllAddressService();
    //用户新增地址
    public void  addAddressService(Address address);
    //查询地址
    public Address selectAddressService(Address address);
    //设置默认地址
    public void setDefaultAddressService(Long id);
    //修改地址
    public void modifyAddressService(Address address);
    //删除地址
    public void deleteAddressService(Long id);
}
