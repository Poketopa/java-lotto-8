package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.BuyAmount;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoResult;
import lotto.service.LottoService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        // 구입 금액 입력
        String rawBuyAmount = inputView.inputBuyAmount();
        Lottos lottos = lottoService.buyLotto(rawBuyAmount);
        // 구입 개수 출력
        outputView.printGeneratedLotto(new LottoResult(lottos.getBuyAmout(), lottos.getLottos()));

        // 당첨 번호 입력
        String rawWinningNumbers = inputView.inputWinningNumber();
        WinningNumbers winningNumbers = lottoService.createWinningNumbers(rawWinningNumbers);
        // 보너스 번호 입력
        String rawBonusNumber = inputView.inputBonusNumber();
        BonusNumber bonusNumber = lottoService.createBonusNumber(rawBonusNumber);


    }
}
