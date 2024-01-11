
package com.pfe.backend.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@FeignClient(name = "ms-invitation", url = "http://localhost:8085/invitations")
public interface InvitationFeignClient {

    @GetMapping("/inv/{entrepriseId}")
    List<Invitation> getInvitationsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId);



    /*@PostMapping("/invitations")
    static void createInvitation(Invitation invitation) {
        throw new UnsupportedOperationException("Unimplemented method 'createInvitation'");
    }*/
}
