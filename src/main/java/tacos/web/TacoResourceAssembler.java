package tacos.web;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.domain.Taco;

/*
*   리스트의 Taco객체를 TacoResource객체로 변화하는 데 필요한 어셈블러 클래스
* */
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {


    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    public TacoResource toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }

    @Override
    protected TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }
}
