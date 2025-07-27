package sky.project.mapper;

import entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    //查找所有地址
    public List<Address> selectAllAddressMapper(Long userId);
    //新增地址
    public void addAddressMapper(Address address);
    //查询某个地址
    public Address selectAddressMapper(Address address);
    //修改地址
    public void modifyAddressMapper(Address address);
    //设置当前所有地址为非默认
    public void setNoDefaultAddressMapper(Long userId);
    //删除地址
    public void deleteAddressMapper(Long id);
}
