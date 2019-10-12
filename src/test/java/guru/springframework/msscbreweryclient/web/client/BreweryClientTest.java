package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;
    @Test
    void testGetBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer(){
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
        URI uri = client.saveNewBeer(beerDto);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUpdateBeer(){
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
        client.updateBeerdto(UUID.randomUUID(),beerDto );
    }

    @Test
    void testDelete(){
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void testGetCustomerById(){
        CustomerDto dto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewCustomer(){
        CustomerDto dto = CustomerDto.builder().customerName("New Custoomer").build();
        URI uri = client.saveNewCustomer(dto);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUpdateCustomer(){
        CustomerDto dto = CustomerDto.builder().customerName("New Customer").build();
        client.updateCustomerDto(UUID.randomUUID(), dto);
    }

    @Test
    void testCustomerDelete(){
        client.deleteCustomer(UUID.randomUUID());
    }
}