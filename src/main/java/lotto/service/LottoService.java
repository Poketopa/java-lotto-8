package lotto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.BonusNumber;
import lotto.domain.BuyAmount;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.util.InputParser;

public class LottoService {
    private final LottoFactory lottoFactory;

    public LottoService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos buyLotto(String rawBuyAmount){
        BuyAmount buyAmount = new BuyAmount(InputParser.parseBuyAmount(rawBuyAmount));

        int lottoCount = buyAmount.getBuyAmount();
        List<Lotto> lottos = new ArrayList<>();
        for(int i=0;i<lottoCount;i++){
            lottos.add(lottoFactory.generateLotto());
        }
        return new Lottos(lottos);
    }

    public WinningNumbers createWinningNumbers(String rawWinningNumbers){
        String[] rawWinningNumbersList = rawWinningNumbers.split(",");
        return new WinningNumbers(Arrays.stream(rawWinningNumbersList)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList());
    }

    public BonusNumber createBonusNumber(String rawBonusNumber){
        int bonusNumber = Integer.parseInt(rawBonusNumber);
        return new BonusNumber(bonusNumber);
    }
}
