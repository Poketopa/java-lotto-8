package lotto.controller;

import java.util.function.Supplier;
import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoResult;
import lotto.dto.ResultResopnse;
import lotto.service.LottoService;
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

    public void run() {
        Lottos lottos = buyLottos();
        outputView.printGeneratedLotto(new LottoResult(lottos.getBuyAmout(), lottos.getLottosToString()));

        WinningNumbers winningNumbers = createWinningNumbers();
        BonusNumber bonusNumber = createBonusNumber(winningNumbers);

        ResultResopnse resultResponse = lottoService.getTotalPrize(lottos, winningNumbers, bonusNumber);
        outputView.printLottoResult(resultResponse);
    }

    private Lottos buyLottos() {
        return retryUntilNoException(() -> lottoService.buyLottos(inputView.inputBuyAmount()));
    }

    private WinningNumbers createWinningNumbers() {
        return retryUntilNoException(() -> lottoService.createWinningNumbers(inputView.inputWinningNumber()));
    }

    private BonusNumber createBonusNumber(WinningNumbers winningNumbers) {
        return retryUntilNoException(
                () -> lottoService.createBonusNumber(inputView.inputBonusNumber(), winningNumbers));
    }

    private <T> T retryUntilNoException(Supplier<T> method) {
        while (true) {
            try {
                return method.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
