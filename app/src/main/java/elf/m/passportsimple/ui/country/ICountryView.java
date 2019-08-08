package elf.m.passportsimple.ui.country;

import java.util.List;

/**
 * Created by jerry on 5/8/18.
 */

public interface ICountryView {
    void getListCountry(List<CountryBean> list);

    void updateListCountry(List<CountryBean> list);

    void showLetter(String letter);

    void hideLetter();
}
