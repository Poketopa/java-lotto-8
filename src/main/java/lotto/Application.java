package lotto;

import lotto.config.Appconfig;
import lotto.controller.LottoController;

public class Application {

    public static void main(String[] args) {
        Appconfig appconfig = new Appconfig();
        LottoController lottoController = appconfig.lottoController();
        lottoController.run();
    }
}
