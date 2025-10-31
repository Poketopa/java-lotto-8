package lotto.controller;

import lotto.domain.BuyAmount;
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
    // 구입 금액 입력

    // 검증

    // 구입 갯수 DTO로 담아서 서비스로 넘김 -> 로또 도메인에서 랜던값 생성

    // 당첨 번호 입력

    // 검증

    // 보너스 번호 입력

    // 검증



}
