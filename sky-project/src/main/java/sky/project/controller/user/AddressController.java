package sky.project.controller.user;

import entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.AddressService;
import utils.BaseContext;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/addressBook")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public Result selectAllAddressController(){
        log.info("查找用户:{}的所有地址", BaseContext.getId());
        List<Address> addressList = addressService.selectAllAddressService();
        return Result.success(addressList);
    }

    @PostMapping
    public Result addAddressController(@RequestBody Address address){
        log.info("用户新增地址:{}",address);
        addressService.addAddressService(address);
        return Result.success();
    }

    @GetMapping("/default")
    public Result selectDefaultAddressController(){
        log.info("查找默认地址");
        Address address = Address.builder().userId(BaseContext.getId()).isDefault(1).build();
        Address addressResult = addressService.selectAddressService(address);
        return Result.success(addressResult);
    }

    @PutMapping("/default")
    public Result setDefaultAddressController(@RequestBody Address address){
        log.info("设置默认地址");
        addressService.setDefaultAddressService(address.getId());
        return Result.success();
    }

    @PutMapping
    public Result modifyAddressController(@RequestBody Address address){
        log.info("修改地址为:{}", address);
        addressService.modifyAddressService(address);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectAddressByIdController(@PathVariable Long id){
        log.info("地址查询回显：{}",id);
        Address address = Address.builder().id(id).build();
        Address addressResult = addressService.selectAddressService(address);
        return Result.success(addressResult);
    }

    @DeleteMapping
    public Result deleteAddressController(Long id){
        log.info("删除地址id:{}",id);
        addressService.deleteAddressService(id);
        return Result.success();
    }
}
