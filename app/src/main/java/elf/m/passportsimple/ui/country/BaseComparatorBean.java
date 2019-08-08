package elf.m.passportsimple.ui.country;

public class BaseComparatorBean {
    //区域的首字母
    private String first_letter;

    public BaseComparatorBean() {
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }
    public BaseComparatorBean(int code, String name, String locale, int flag) {

        String pinyin = PinyinUtils.getPingYin(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            setFirst_letter(sortString.toUpperCase());
        } else {
            setFirst_letter("#");
        }
    }
}
