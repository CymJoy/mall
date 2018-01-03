package com.neusoft.mall.address;

import com.neusoft.mall.address.domain.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by chenyingmiao on 2017/11/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressControllerTest {

    @Autowired
    AddressService addressService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findUserShippingAddress() throws Exception {
        System.out.println("--------"+this.addressService.findUserShippingAddress("1"));
    }

    @Test
    public void findById() throws Exception {
        Address byId = this.addressService.findById("2");
        System.out.println("--------"+byId);
    }

    @Test
    public void addAddress() throws Exception {
        Address address = new Address();
        address.setUserId("1");
        address.setId("2");
        address.setStatus(1);
        this.addressService.addAddress(address);
    }

    @Test
    public void deleteAddress() throws Exception {
        this.addressService.deleteAddress("1","2");
    }

    @Test
    public void updateAddress() throws Exception {
        Address byId = this.addressService.findById("2");
        byId.setAddress("ssssssssqqqqqqqqqq");
        this.addressService.updateAddress(byId);
    }
}