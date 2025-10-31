package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.LottoFactory;
import lotto.domain.LottoGenerator;
import lotto.domain.RandomNumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Appconfig {
    public LottoGenerator lottoGenerator(){
        return new RandomNumberGenerator();
    }

    public LottoFactory lottoFactory(){
        return new LottoFactory(lottoGenerator());
    }

    public LottoService lottoService(){
        return new LottoService(lottoFactory());
    }

    public InputView inputView(){
        return new InputView();
    }

    public OutputView outputView(){
        return new OutputView();
    }

    public LottoController lottoController(){
        return new LottoController(lottoService(), inputView(), outputView());
    }
}
