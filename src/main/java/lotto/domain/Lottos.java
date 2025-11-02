package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public List<String> getLottosToString() {
        return lottos.stream()
                .map(lotto -> "[" + lotto.getNumbers().stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(java.util.stream.Collectors.joining(", ")) + "]")
                .collect(java.util.stream.Collectors.toList());
    }

    public int getBuyAmout() {
        return lottos.size();
    }
}
