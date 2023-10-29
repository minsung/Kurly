package ms.study.kurly.domain.terms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Response;
import ms.study.kurly.domain.terms.dto.TermsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "이용 약관")
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsService service;

    @Operation(summary = "이용 약관 조회")
    @GetMapping("/terms")
    public Response<List<TermsResponse>> terms() {

        return service.terms();
    }
}
