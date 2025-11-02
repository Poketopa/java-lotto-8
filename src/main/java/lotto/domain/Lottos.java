package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validate(lottos);
        this.lottos = lottos;
    }

    private void validate(List<Lotto> lottos){

    }

    public List<Lotto> getLottos(){
        return new ArrayList<>(lottos);
    }

    public List<String> getLottosToString(){
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.toList());
    }

    public int getBuyAmout(){
        return lottos.size();
    }
}
