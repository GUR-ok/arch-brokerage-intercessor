package ru.gur.archintercessor.interaction.claim.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindClaimsResponse {

    private List<ClaimDto> claims;
}
