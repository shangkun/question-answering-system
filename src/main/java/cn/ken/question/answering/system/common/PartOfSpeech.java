package cn.ken.question.answering.system.common;

/**
 * author: shangkun <br/>
 * date: 2018/7/15 <br/>
 * what: 词性 <br/>
 */
public enum PartOfSpeech {
    A("a","形容词"),
    AD("ad","副形词"),
    AG("ag","形容词性语素"),
    AL("al","形容词性惯用语"),
    AN("an","名形词"),
    B("b","区别词"),
    BEGIN("begin","仅用于始##始"),
    BG("bg","区别语素"),
    BL("bl","区别词性惯用语"),
    C("c","连词"),
    CC("cc","并列连词"),
    D("d","副词"),
    DG("dg","辄,俱,复之类的副词"),
    DL("dl","连语"),
    E("e","叹词"),
    END("end","仅用于终##终"),
    F("f","方位词"),
    G("g","学术词汇"),
    GB("gb","生物相关词汇"),
    GBC("gbc","生物类别"),
    GC("gc","化学相关词汇"),
    GG("gg","地理地质相关词汇"),
    GI("gi","计算机相关词汇"),
    GM("gm","数学相关词汇"),
    GP("gp","物理相关词汇"),
    H("h","前缀"),
    I("i","成语"),
    J("j","简称略语"),
    K("k","后缀"),
    L("l","习用语"),
    M("m","数词"),
    mg("mg","数语素"),
    Mg("Mg","甲乙丙丁之类的数词"),
    MQ("mq","数量词"),
    N("n","名词"),
    NB("nb","生物名"),
    NBA("nba","动物名"),
    NBC("nbc","动物纲目"),
    NBP("nbp","植物名"),
    NF("nf","食品，比如“薯片”"),
    NG("ng","名词性语素"),
    NH("nh","医药疾病等健康相关名词"),
    NHD("nhd","疾病"),
    NHM("nhm","药品"),
    NI("ni","机构相关（不是独立机构名）"),
    NIC("nic","下属机构"),
    NIS("nis","机构后缀"),
    NIT("nit","教育相关机构"),
    NL("nl","名词性惯用语"),
    NM("nm","物品名"),
    NMC("nmc","化学品名"),
    NN("nn","工作相关名词"),
    NND("nnd","职业"),
    NNT("nnt","职务职称"),
    NR("nr","人名"),
    NR1("nr1","复姓"),
    NR2("nr2","蒙古姓名"),
    NRF("nrf","音译人名"),
    NRJ("nrj","日语人名"),
    NS("ns","地名"),
    NSF("nsf","音译地名"),
    NT("nt","机构团体名"),
    NTC("ntc","公司名"),
    NTCB("ntcb","银行"),
    NTCF("ntcf","工厂"),
    NTCH("ntch","酒店宾馆"),
    NTH("nth","医院"),
    NTO("nto","政府机构"),
    NTS("nts","中小学"),
    NTU("ntu","大学"),
    NX("nx","字母专名"),
    NZ("nz","其他专名"),
    O("o","拟声词"),
    P("p","介词"),
    PBA("pba","介词“把”"),
    PBEI("pbei","介词“被”"),
    Q("q","量词"),
    QG("qg","量词语素"),
    QT("qt","时量词"),
    QV("qv","动量词"),
    R("r","代词"),
    rg("rg","代词性语素"),
    Rg("Rg","古汉语代词性语素"),
    RR("rr","人称代词"),
    RY("ry","疑问代词"),
    RYS("rys","处所疑问代词"),
    RYT("ryt","时间疑问代词"),
    RYV("ryv","谓词性疑问代词"),
    RZ("rz","指示代词"),
    RZS("rzs","处所指示代词"),
    RZT("rzt","时间指示代词"),
    RZV("rzv","谓词性指示代词"),
    S("s","处所词"),
    T("t","时间词"),
    TG("tg","时间词性语素"),
    U("u","助词"),
    UD("ud","助词"),
    UDE1("ude1","的底"),
    UDE2("ude2","地"),
    UDE3("ude3","得"),
    UDENG("udeng","等等等云云"),
    UDH("udh","的话"),
    UG("ug","过"),
    UGUO("uguo","过"),
    UJ("uj","助词"),
    UL("ul","连词"),
    ULE("ule","了喽"),
    ULIAN("ulian","连（“连小学生都会”）"),
    ULS("uls","来讲来说而言说来"),
    USUO("usuo","所"),
    UV("uv","连词"),
    UYY("uyy","一样一般似的般"),
    UZ("uz","着"),
    UZHE("uzhe","着"),
    UZHI("uzhi","之"),
    V("v","动词"),
    VD("vd","副动词"),
    VF("vf","趋向动词"),
    VG("vg","动词性语素"),
    VI("vi","不及物动词（内动词）"),
    VL("vl","动词性惯用语"),
    VN("vn","名动词"),
    VSHI("vshi","动词“是”"),
    VX("vx","形式动词"),
    VYOU("vyou","动词“有”"),
    W("w","标点符号"),
    WB("wb","百分号千分号，全角：％‰半角：%"),
    WD("wd","逗号，全角：，半角：,"),
    WF("wf","分号，全角：；半角：;"),
    WH("wh","单位符号，全角：￥＄￡°℃半角：$"),
    WJ("wj","句号，全角：。"),
    WKY("wky","右括号，全角：）〕］｝》】〗〉半角：)]{>"),
    WKZ("wkz","左括号，全角：（〔［｛《【〖〈半角：([{<"),
    WM("wm","冒号，全角：：半角：:"),
    WN("wn","顿号，全角：、"),
    WP("wp","破折号，全角：——－－——－半角：——-"),
    WS("ws","省略号，全角：………"),
    WT("wt","叹号，全角：！"),
    WW("ww","问号，全角：？"),
    WYY("wyy","右引号，全角：”’』"),
    WYZ("wyz","左引号，全角：“‘『"),
    X("x","字符串"),
    XU("xu","网址URL"),
    XX("xx","非语素字"),
    Y("y","语气词(deleteyg)"),
    YG("yg","语气语素"),
    Z("z","状态词"),
    ZG("zg","状态词"),
    BUSINESS("business","业务词");

    private PartOfSpeech(String key,String value){
        this.key=key;
        this.value=value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String key) {
        try {
            for(PartOfSpeech partOfSpeech:PartOfSpeech.values()){
                if(key.equals(partOfSpeech.key)){
                    return partOfSpeech.value;
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setValue(String value) {
        this.value = value;
    }
}
