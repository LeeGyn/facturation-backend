package com.etslyam.facturationbackend.webs;

import static org.mockito.Mockito.when;

import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.inputs.ProduitsEvents;
import com.etslyam.facturationbackend.utils.beans.ResponseImpl;
import com.etslyam.facturationbackend.utils.beans.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProduitController.class})
@ExtendWith(SpringExtension.class)
class ProduitControllerDiffblueTest {
    @Autowired
    private ProduitController produitController;

    @MockBean
    private ProduitsEvents produitsEvents;

    /**
     * Method under test: {@link ProduitController#findalldeletefalse()}
     */
    @Test
    void testFindalldeletefalse() throws Exception {
        when(produitsEvents.findProduitsByDeletedFalse())
                .thenReturn(new ResponseImpl<>(Status.PROCESSING, "Not all who wander are lost"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findall-produits");
        MockMvcBuilders.standaloneSetup(produitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"fieldErrors\":[],\"hasError\":false,\"message\":\"Not all who wander are lost\",\"status\":102}"));
    }

    /**
     * Method under test: {@link ProduitController#findalldeletefalse()}
     */
    @Test
    void testFindalldeletefalse2() throws Exception {
        ResponseImpl<ResponseProduitDTO> responseImpl = new ResponseImpl<>(Status.PROCESSING,
                "Not all who wander are lost");
        responseImpl.setHasError(true);
        when(produitsEvents.findProduitsByDeletedFalse()).thenReturn(responseImpl);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findall-produits");
        MockMvcBuilders.standaloneSetup(produitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"fieldErrors\":[],\"hasError\":true,\"message\":\"Not all who wander are lost\",\"status\":102}"));
    }
}

