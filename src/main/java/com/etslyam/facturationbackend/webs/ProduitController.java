package com.etslyam.facturationbackend.webs;

import com.etslyam.facturationbackend.dtos.RequestProduitDTO;
import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.inputs.ProduitsEvents;
import com.etslyam.facturationbackend.utils.beans.Messages;
import com.etslyam.facturationbackend.utils.beans.Response;
import com.etslyam.facturationbackend.utils.beans.ResponseImpl;
import com.etslyam.facturationbackend.utils.beans.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProduitController {

    private final ProduitsEvents produitsEvents;

    public ProduitController(ProduitsEvents produitsEvents) {
        this.produitsEvents = produitsEvents;
    }

    @PostMapping(path = "/save-produits")
    public Response<ResponseProduitDTO> save(@RequestBody RequestProduitDTO dto){
        Response<ResponseProduitDTO> _Response = null;
        System.out.println(dto.toString());
        if (dto.getDesignation() == null || dto.getQuantite() == null || dto.getPrix() == null ){
            _Response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.BLOCKED)
                    .message(Messages.INVALID_REQUEST)
                    .hasError(true)
                    .build();
        }else {
            _Response = produitsEvents.save(dto);
        }
        return _Response;
    }

    @GetMapping("/findall-produits")
    public Response<ResponseProduitDTO> findalldeletefalse(){
        Response<ResponseProduitDTO> _Response = null;
        _Response = produitsEvents.findProduitsByDeletedFalse();
        return _Response;
    }

    @PostMapping("/searchby-produitsname")
    public Response<ResponseProduitDTO> searchbydesignation(@RequestBody RequestProduitDTO dto){
        Response<ResponseProduitDTO> _Response = null;
        if (dto.getDesignation() == null){
            _Response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.BLOCKED)
                    .message(Messages.INVALID_REQUEST)
                    .hasError(true)
                    .build();
        }else {
            _Response = produitsEvents.findProduitByDesignation(dto);
        }
        return _Response;
    }

}
