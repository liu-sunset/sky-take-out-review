package sky.project.service.impl;

import entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sky.project.mapper.AddressMapper;
import sky.project.service.AddressService;
import utils.BaseContext;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    //查找用户的所有地址
    @Override
    public List<Address> selectAllAddressService() {
        return addressMapper.selectAllAddressMapper(BaseContext.getId());
    }

    //用户新增地址
    @Override
    public void addAddressService(Address address) {
        address.setUserId(BaseContext.getId());
        addressMapper.addAddressMapper(address);
    }

    //查询某个地址
    @Override
    public Address selectAddressService(Address address) {
       return addressMapper.selectAddressMapper(address);
    }

    //设置默认地址
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefaultAddressService(Long id) {
        Address address = Address.builder().id(id).isDefault(1).build();
        //将当前用户所有地址设置成非默认
        addressMapper.setNoDefaultAddressMapper(BaseContext.getId());
        addressMapper.modifyAddressMapper(address);
    }

    //修改地址
    @Override
    public void modifyAddressService(Address address) {
        addressMapper.modifyAddressMapper(address);
    }

    //删除地址
    @Override
    public void deleteAddressService(Long id) {
        addressMapper.deleteAddressMapper(id);
    }
}
