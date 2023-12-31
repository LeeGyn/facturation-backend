package com.etslyam.facturationbackend.webs;

import com.etslyam.facturationbackend.dtos.RequestCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseCommandeDTO;
import com.etslyam.facturationbackend.inputs.CommandeEvents;
import com.etslyam.facturationbackend.utils.beans.Messages;
import com.etslyam.facturationbackend.utils.beans.Response;
import com.etslyam.facturationbackend.utils.beans.ResponseImpl;
import com.etslyam.facturationbackend.utils.beans.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CommandeController {
    private final CommandeEvents commandeEvents;

    public CommandeController(CommandeEvents commandeEvents) {
        this.commandeEvents = commandeEvents;
    }


    @PostMapping("/save-commande")
    public Response<ResponseCommandeDTO> save(@RequestBody RequestCommandeDTO dto){
        Response<ResponseCommandeDTO> _Response = null;
        if (dto.getRequestLigneCommandeDTOS().isEmpty()){
            _Response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.BLOCKED)
                    .message(Messages.INVALID_REQUEST)
                    .hasError(true)
                    .build();
        }else {
            _Response = commandeEvents.save(dto);
        }
        return _Response;
    }

    @GetMapping("/findall-commandes")
    public Response<ResponseCommandeDTO> findalldeletefalse(){
        Response<ResponseCommandeDTO> _Response = null;
        _Response = commandeEvents.findByDeletedFalseAndArchivedFalseAndDisabledFalseAndActiveTrueAndPublicsTrue();
        return _Response;
    }

    @PostMapping("/details-commandes")
    public Response<ResponseCommandeDTO> details(@RequestBody RequestCommandeDTO dto){
        Response<ResponseCommandeDTO> _Response = null;
        _Response = commandeEvents.findByCode(dto);
        return _Response;
    }
}
