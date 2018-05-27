package com.dyg.test.dto;


public class Data {
    // Data  (グループ名)  -  -
    // 保険会社コード  保険会社コード  半角文字  2  生保協会ベースの保険会社コード（英字）を設定、太陽保険は"HD"
    private String inscoCd = "HD";
    // 共同GW利用ID  共同GW利用ID  半角文字  20  代理店担当者の共同GW利用ID
    // ※保険共同GWから生保Ｗｅｂ画面へ引き渡すＨＴＴＰヘッダ情報内の”Ｕid”（共同GW利用ID）を設 定
    // ※本項目はどの代理店へデータ送信を行うかを共同GWで判断するために利用
    private String gwId = "";
    // 設計書番号  設計書番号  半角文字  20  設計書番号を設定
    private String desDocNo = "";
    // 申込書番号  申込書番号  半角文字  20  申込書番号を設定
    //（新規データ還元時設定必須）
    private String applNo = "";
    // 証券番号  証券番号  半角文字  23  証券番号を設定
    private String syokenNo = "";
    // 案件管理番号  案件管理番号  半角文字  15  代理店の案件管理番号を設定
    // ※代理店にて設計書と申込書を紐づけるキー項目
    private String caseManageNo = "";
    // ステータス  ステータス  半角数字  2  以下の値を設定""00"" 未承認、""01"" 承認済み
    // （新規データ還元時設定必須）
    private String applSt = "";
    // 設計書作成日  設計書作成日  半角数字  8  YYYYMMDD：Web上で設計書が作成された年月日を設定(年・月・日 各々前ZERO)
    //（新規データ還元時設定必須）
    private String desDocNoDate = "";
    // 申込日  申込日  半角数字  8  YYYYMMDD：Web上で申込がされた年月日を設定(年・月・日 各々前ZERO)
    //（新規データ還元時設定必須）
    private String applDate = "";
    // 取扱  (グループ名)  -  -
    // 担当店番  店番  半角文字  9  代理店内において所属する部門を表すコードを設定
    //（新規データ還元時設定必須）
    private String branchCode = "";
    // 担当者  (グループ名)  -  -
    // 募集人名-漢字  募集人名（漢字）  全半角文字  60  募集人名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    // ※募集人は意向確認書作成、又は被保険者電子署名の際にWeb操作したユーザを指す
    private String employeeNameKanji = "";
    // 取扱承認者-漢字  取扱承認者（漢字）  全半角文字  60  取扱承認者名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    // ※取扱承認者は上長承認をWeb操作したユーザを指す
    private String toriatsukaiNameKanji = "";
    // 契約者  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  契約者名（半角カナ）  半角文字  120  契約者名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String kyksNameKatakana = "";
    // 漢字  契約者名（漢字）  全半角文字  60  契約者名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String kyksNameKanji = "";
    // 生年月日  契約者生年月日  半角数字  8  YYYYMMDD：契約者の生年月日を設定(年・月・日 各々前ZERO)
    private String kyksBirth = "";
    // 性別  契約者性別  半角文字  1  コード値は1700バイト（契約者性別）準拠とします
    private String kyksGender = "";
    // 住所-漢字  契約者住所（漢字）  全半角文字  120  契約者住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String kyksAddressKanji = "";
    // 勤務先名  (グループ名)  -  -
    // 名称-半角カナ  契約者勤務先（半角カナ）  半角文字  50  契約者の勤務先及び勤務先所属（半角カナ）を設定
    private String kyksCompanyNameKatakana = "";
    // 名称-漢字  契約者勤務先（漢字）  全半角文字  60  契約者の勤務先及び勤務先所属（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String kyksCompanyNameKanji = "";
    // 被保険者  (グループ名)  -  -  契被同一の場合、契約者の同項目と同一値を設定
    // 氏名  (グループ名)  -  -
    // 半角カナ  被保険者名（半角カナ）  半角文字  120  被保険者名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    // ※契被同一の場合は、契約者名（半角カナ）と同じ内容を設定
    private String hihoNameKatakana = "";
    // 漢字  被保険者名（漢字）  全半角文字  60  被保険者名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    // ※契被同一の場合は、契約者名（漢字）と同じ内容を設定
    private String hihoNameKanji = "";
    // 生年月日  被保険者生年月日  半角数字  8  YYYYMMDD：被保険者の生年月日を設定(年・月・日 各々前ZERO)
    // ※契被同一の場合は、契約者生年月日と同じ内容を設定
    private String hihoBirth = "";
    // 年齢  被保険者年齢  半角数字  3  被保険者の年齢を設定（保険料算出基準年齢）
    // ※出生前の場合は、初期値 // ※契被同一の場合は、契約者の年齢を設定
    private String hihoAge = "";
    // 性別  被保険者性別  半角文字  1  コード値は1700バイト（被保険者性別）準拠とします
    // ※契被同一の場合は、契約者性別と同じ内容を設定
    private String hihoGender = "";
    // 住所-漢字  被保険者住所（漢字）  全半角文字  120  被保険者住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※契被同一の場合は、契約者住所（漢字）と同じ内容を設定
    private String hihoAddressKanji = "";
    // 勤務先名  (グループ名)  -  -
    // 名称-半角カナ  被保険者勤務先（半角カナ）  半角文字  50  被保険者の勤務先及び勤務先所属（半角カナ）を設定
    // ※契被同一の場合は、契約者勤務先（半角カナ）と同じ内容を設定
    private String hihoCompanyNameKatakana = "";
    // 名称-漢字  被保険者勤務先（漢字）  全半角文字  60  被保険者の勤務先及び勤務先所属（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※契被同一の場合は、契約者勤務先（漢字）と同じ内容を設定
    private String hihoCompanyNameKanji = "";
    // 受取人  (グループ名)  -  -
    // 受取人1  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  受取人名１（半角カナ）  半角文字  120  受取人１の氏名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String uketori1NameKatakana = "";
    // 漢字  受取人名１（漢字）  全半角文字  60  受取人１の氏名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String uketori1NameKanji = "";
    // 生年月日  受取人生年月日１  半角数字  8  YYYYMMDD：受取人１の生年月日を設定(年・月・日 各々前ZERO)
    private String uketori1Birth = "";
    // 住所-漢字  受取人住所１（漢字）  全半角文字  120  受取人１の住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String uketori1AddressKanji = "";
    // 受取人2  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  受取人名２（半角カナ）  半角文字  120  受取人２の氏名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String uketori2NameKatakana = "";
    // 漢字  受取人名２（漢字）  全半角文字  60  受取人２の氏名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String uketori2NameKanji = "";
    // 生年月日  受取人生年月日２  半角数字  8  YYYYMMDD：受取人２の生年月日を設定(年・月・日 各々前ZERO)
    private String uketori2Birth = "";
    // 住所-漢字  受取人住所２（漢字）  全半角文字  120  受取人２の住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String uketori2AddressKanji = "";
    // 受取人3  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  受取人名３（半角カナ）  半角文字  120  受取人３の氏名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String uketori3NameKatakana = "";
    // 漢字  受取人名３（漢字）  全半角文字  60  受取人３の氏名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String uketori3NameKanji = "";
    // 生年月日  受取人生年月日３  半角数字  8  YYYYMMDD：受取人３の生年月日を設定(年・月・日 各々前ZERO)
    private String uketori3Birth = "";
    // 住所-漢字  受取人住所３（漢字）  全半角文字  120  受取人３の住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String uketori3AddressKanji = "";
    // 受取人4  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  受取人名４（半角カナ）  半角文字  120  受取人４の氏名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String uketori4NameKatakana = "";
    // 漢字  受取人名４（漢字）  全半角文字  60  受取人４の氏名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String uketori4NameKanji = "";
    // 生年月日  受取人生年月日４  半角数字  8  YYYYMMDD：受取人４の生年月日を設定(年・月・日 各々前ZERO)
    private String uketori4Birth = "";
    // 住所-漢字  受取人住所４（漢字）  全半角文字  120  受取人４の住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String uketori4AddressKanji = "";
    // 受取人5  (グループ名)  -  -
    // 氏名  (グループ名)  -  -
    // 半角カナ  受取人名５（半角カナ）  半角文字  120  受取人５の氏名（半角カナ）を設定
    // ※可能であればカナ姓とカナ名の間を半角SPACEで区切る
    private String uketori5NameKatakana = "";
    // 漢字  受取人名５（漢字）  全半角文字  60  受取人５の氏名（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    // ※可能であれば漢字姓と漢字名の間を全角ＳＰＡＣＥで区切る
    private String uketori5NameKanji = "";
    // 生年月日  受取人生年月日５  半角数字  8  YYYYMMDD：受取人５の生年月日を設定(年・月・日 各々前ZERO)
    private String uketori5Birth = "";
    // 住所-漢字  受取人住所５（漢字）  全半角文字  120  受取人５の住所（漢字）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    private String uketori5AddressKanji = "";
    // 契約内容  (グループ名)  -  -
    // 保険料払込方法  保険料払込方法  半角文字  1  コード値は1700バイト（払込方法）準拠とします
    //（新規データ還元時設定必須）
    private String haraikomiHoho = "";
    // 合計保険料  (グループ名)  -  -
    // 保険料  払込金額  半角数字  11  払込金額の値を設定。
    //一時払の場合は一時払保険料を設定
    //平準払の場合は初回保険料を設定
    //外貨の場合は補助通貨単位にて設定
    //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
    // ※保険料とは主契約保険料＋特約保険料＋前納保険料を表します。
    //（新規データ還元時設定必須）
    private String amount = "";
    // 通貨コード  通貨コード（払込金）  半角文字  3  払込金の通貨コードを設定
    // コード値は生保汎用版手数料データ（通貨コード）準拠とします。
    // （新規データ還元時設定必須）
    private String haraikomiTukaCode = "";
    // 為替レート  為替レート（保険料）  半角数字  10  保険料等の円換算時の為替レートを設定
    // 支払金通貨が円の場合はZEROを設定
    //《整数部：前方7桁。小数部：後方3桁。左詰め後0設定。》
    // （例）103.50⇒103500 123.00⇒123000
    // ※保険料が外貨の商品については新規データ還元時設定必須
    private String haraikomiExRate = "";
    // 全期前納保険料  全期前納保険料  半角数字  11  全期前納の場合の前納保険料を設定
    // 外貨建ての場合は補助通貨単位にて設定
    //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
    // ※一部前納の場合も設定
    private String zenkiZennoHokenryo = "";
    // 初回払込金額  初回払込保険料  半角数字  11  初回払込金額を設定
    // ※数回分まとめて保険料を払い込む場合、数回分の合計値を設定
    //外貨建ての場合は補助通貨単位にて設定
    //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
    // （新規データ還元時設定必須）
    private String syokaiHaraikomiHokenryo = "";
    // 主契約商品名-漢字  主契約商品名（漢字）  全半角文字  120  保険商品名（漢字）（ペットネーム）を設定
    // ※ひらがな／カタカナ／英数字の設定可
    //（新規データ還元時設定必須）
    private String syukeiyakuSyohinMei = "";
    // 主契約商品番号  主契約商品番号  半角文字  7  各保険会社が商品毎に付与する独自コードを設定
    // ※データ連携項目「主契約商品番号」と同じ値を設定
    // （新規データ還元時設定必須）
    private String syukeiyakuSyohinNo = "";
    // 主契約保険期間  主契約保険期間  半角文字  3  年満期の場合は、保険期間を設定（単位：年）
    // 歳満期の場合は、満期時年齢を設定
    // 終身の場合は、ZEROを設定
    //（新規データ還元時設定必須）
    private String syukeiyakuKikan = "";
    // 主契約保険期間区分  主契約保険期間区分  半角文字  1  コード値は1700バイト（保険期間区分）準拠とします
    //（新規データ還元時設定必須）
    private String syukeiyakuKikanKubun = "";
    // 主契約保険料払込期間  主契約保険料払込期間  半角文字  3  年払込の場合は、保険期間を設定（単位：年）
    // 歳払込の場合は、払込終了時年齢を設定
    // 一時払込、終身払込の場合は、ZEROを設定
    private String syukeiyakuHokenryoHaraikomiKikan = "";
    // 主契約保険料払込期間区分  主契約保険料払込期間区分  半角文字  1  コード値は1700バイト（払込期間区分）準拠とします
    // ※一時払込の場合は初期値を設定
    private String syukeiyakuHokenryoHaraikomiKikanKubun = "";
    // 主契約保険金  (グループ名)  -  -
    // 保険金額-年金金額  主契約保険金額／年金金額  半角数字  11  主契約保険金または年金金額を設定
    // 外貨建ての場合は、補助通貨単位にて設定
    // ※死亡保険金、満期保険金、年金年額等の商品の特性に応じた金額を設定
    //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
    // （原則設定必須）
    private String syukeiyakuHokenNenkinKingaku = "";
    // 通貨コード  通貨コード（運用通貨）  半角文字  3  運用通貨の通貨コードを設定
    // コード値は生保汎用版手数料データ（通貨コード）準拠とします。
    // （原則設定必須）
    private String tuyotukaCode = "";
    // 為替レート  為替レート（運用通貨）  半角数字  10  運用通貨の円換算時の為替レートを設定
    // 運用通貨が円の場合はZEROを設定
    //《整数部：前方7桁。小数部：後方3桁。左詰め後0設定。》
    // （例）103.50⇒103500 123.00⇒123000
    // ※運用通貨が外貨の商品については新規データ還元時設定必須
    private String tuyotukaExRate = "";
    // 給付金  (グループ名)  -  -
    // 診断給付金-疾病  診断給付金-疾病  半角数字  11  診断給付金-疾病の１保険事故あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String shindanKyufukinKibyo = "";
    // 診断給付金-要介護  診断給付金-要介護  半角数字  11  診断給付金-要介護の１保険事故あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String shindanKyufukinYokaigo = "";
    // 入院給付金-特定疾病  入院給付金-特定疾病  半角数字  11  入院給付金-特定疾病の入院１日あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String nyuinkyufukinTokuteiKibyo = "";
    // 入院給付金-通常  入院給付金-通常  半角数字  11  入院給付金-通常の入院１日あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String nyuinkyufukinTujyo = "";
    // 手術給付金-特定疾病  手術給付金-特定疾病  半角数字  11  手術給付金-通常の１保険事故あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String syujyutukyufukinTokuteiKibyo = "";
    // 手術給付金-通常  手術給付金-通常  半角数字  11  手術給付金-通常の１保険事故あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String syujyutukyufukinTujyo = "";
    // 生存給付金  生存給付金  半角数字  11  生存給付金の１か月あたりの金額を設定
    // ※該当の給付金が発生する商品については新規データ還元時設定必須
    private String seizonkyufukin = "";
    // 据置期間  据置期間  半角数字  3  据置期間を設定（単位：年）
    private String sueokiKikan = "";
    // 年間年金支払回数  年間年金支払回数  半角数字  3  年間の年金支払回数を設定
    // ※年金支払開始前、年金商品以外の場合は未使用（ZERO）
    // ※年金型商品については新規データ還元時設定必須
    private String nenkannenkinShiharaiKaisu = "";

    public Data() {
    }

    public Data(String desDocNo) {
        this.desDocNo = desDocNo;
    }

    public String getInscoCd() {
        return inscoCd;
    }

    public void setInscoCd(String inscoCd) {
        this.inscoCd = inscoCd;
    }

    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    public String getDesDocNo() {
        return desDocNo;
    }

    public void setDesDocNo(String desDocNo) {
        this.desDocNo = desDocNo;
    }

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getSyokenNo() {
        return syokenNo;
    }

    public void setSyokenNo(String syokenNo) {
        this.syokenNo = syokenNo;
    }

    public String getCaseManageNo() {
        return caseManageNo;
    }

    public void setCaseManageNo(String caseManageNo) {
        this.caseManageNo = caseManageNo;
    }

    public String getApplSt() {
        return applSt;
    }

    public void setApplSt(String applSt) {
        this.applSt = applSt;
    }

    public String getDesDocNoDate() {
        return desDocNoDate;
    }

    public void setDesDocNoDate(String desDocNoDate) {
        this.desDocNoDate = desDocNoDate;
    }

    public String getApplDate() {
        return applDate;
    }

    public void setApplDate(String applDate) {
        this.applDate = applDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getEmployeeNameKanji() {
        return employeeNameKanji;
    }

    public void setEmployeeNameKanji(String employeeNameKanji) {
        this.employeeNameKanji = employeeNameKanji;
    }

    public String getToriatsukaiNameKanji() {
        return toriatsukaiNameKanji;
    }

    public void setToriatsukaiNameKanji(String toriatsukaiNameKanji) {
        this.toriatsukaiNameKanji = toriatsukaiNameKanji;
    }

    public String getKyksNameKatakana() {
        return kyksNameKatakana;
    }

    public void setKyksNameKatakana(String kyksNameKatakana) {
        this.kyksNameKatakana = kyksNameKatakana;
    }

    public String getKyksNameKanji() {
        return kyksNameKanji;
    }

    public void setKyksNameKanji(String kyksNameKanji) {
        this.kyksNameKanji = kyksNameKanji;
    }

    public String getKyksBirth() {
        return kyksBirth;
    }

    public void setKyksBirth(String kyksBirth) {
        this.kyksBirth = kyksBirth;
    }

    public String getKyksGender() {
        return kyksGender;
    }

    public void setKyksGender(String kyksGender) {
        this.kyksGender = kyksGender;
    }

    public String getKyksAddressKanji() {
        return kyksAddressKanji;
    }

    public void setKyksAddressKanji(String kyksAddressKanji) {
        this.kyksAddressKanji = kyksAddressKanji;
    }

    public String getKyksCompanyNameKatakana() {
        return kyksCompanyNameKatakana;
    }

    public void setKyksCompanyNameKatakana(String kyksCompanyNameKatakana) {
        this.kyksCompanyNameKatakana = kyksCompanyNameKatakana;
    }

    public String getKyksCompanyNameKanji() {
        return kyksCompanyNameKanji;
    }

    public void setKyksCompanyNameKanji(String kyksCompanyNameKanji) {
        this.kyksCompanyNameKanji = kyksCompanyNameKanji;
    }

    public String getHihoNameKatakana() {
        return hihoNameKatakana;
    }

    public void setHihoNameKatakana(String hihoNameKatakana) {
        this.hihoNameKatakana = hihoNameKatakana;
    }

    public String getHihoNameKanji() {
        return hihoNameKanji;
    }

    public void setHihoNameKanji(String hihoNameKanji) {
        this.hihoNameKanji = hihoNameKanji;
    }

    public String getHihoBirth() {
        return hihoBirth;
    }

    public void setHihoBirth(String hihoBirth) {
        this.hihoBirth = hihoBirth;
    }

    public String getHihoAge() {
        return hihoAge;
    }

    public void setHihoAge(String hihoAge) {
        this.hihoAge = hihoAge;
    }

    public String getHihoGender() {
        return hihoGender;
    }

    public void setHihoGender(String hihoGender) {
        this.hihoGender = hihoGender;
    }

    public String getHihoAddressKanji() {
        return hihoAddressKanji;
    }

    public void setHihoAddressKanji(String hihoAddressKanji) {
        this.hihoAddressKanji = hihoAddressKanji;
    }

    public String getHihoCompanyNameKatakana() {
        return hihoCompanyNameKatakana;
    }

    public void setHihoCompanyNameKatakana(String hihoCompanyNameKatakana) {
        this.hihoCompanyNameKatakana = hihoCompanyNameKatakana;
    }

    public String getHihoCompanyNameKanji() {
        return hihoCompanyNameKanji;
    }

    public void setHihoCompanyNameKanji(String hihoCompanyNameKanji) {
        this.hihoCompanyNameKanji = hihoCompanyNameKanji;
    }

    public String getUketori1NameKatakana() {
        return uketori1NameKatakana;
    }

    public void setUketori1NameKatakana(String uketori1NameKatakana) {
        this.uketori1NameKatakana = uketori1NameKatakana;
    }

    public String getUketori1NameKanji() {
        return uketori1NameKanji;
    }

    public void setUketori1NameKanji(String uketori1NameKanji) {
        this.uketori1NameKanji = uketori1NameKanji;
    }

    public String getUketori1Birth() {
        return uketori1Birth;
    }

    public void setUketori1Birth(String uketori1Birth) {
        this.uketori1Birth = uketori1Birth;
    }

    public String getUketori1AddressKanji() {
        return uketori1AddressKanji;
    }

    public void setUketori1AddressKanji(String uketori1AddressKanji) {
        this.uketori1AddressKanji = uketori1AddressKanji;
    }

    public String getUketori2NameKatakana() {
        return uketori2NameKatakana;
    }

    public void setUketori2NameKatakana(String uketori2NameKatakana) {
        this.uketori2NameKatakana = uketori2NameKatakana;
    }

    public String getUketori2NameKanji() {
        return uketori2NameKanji;
    }

    public void setUketori2NameKanji(String uketori2NameKanji) {
        this.uketori2NameKanji = uketori2NameKanji;
    }

    public String getUketori2Birth() {
        return uketori2Birth;
    }

    public void setUketori2Birth(String uketori2Birth) {
        this.uketori2Birth = uketori2Birth;
    }

    public String getUketori2AddressKanji() {
        return uketori2AddressKanji;
    }

    public void setUketori2AddressKanji(String uketori2AddressKanji) {
        this.uketori2AddressKanji = uketori2AddressKanji;
    }

    public String getUketori3NameKatakana() {
        return uketori3NameKatakana;
    }

    public void setUketori3NameKatakana(String uketori3NameKatakana) {
        this.uketori3NameKatakana = uketori3NameKatakana;
    }

    public String getUketori3NameKanji() {
        return uketori3NameKanji;
    }

    public void setUketori3NameKanji(String uketori3NameKanji) {
        this.uketori3NameKanji = uketori3NameKanji;
    }

    public String getUketori3Birth() {
        return uketori3Birth;
    }

    public void setUketori3Birth(String uketori3Birth) {
        this.uketori3Birth = uketori3Birth;
    }

    public String getUketori3AddressKanji() {
        return uketori3AddressKanji;
    }

    public void setUketori3AddressKanji(String uketori3AddressKanji) {
        this.uketori3AddressKanji = uketori3AddressKanji;
    }

    public String getUketori4NameKatakana() {
        return uketori4NameKatakana;
    }

    public void setUketori4NameKatakana(String uketori4NameKatakana) {
        this.uketori4NameKatakana = uketori4NameKatakana;
    }

    public String getUketori4NameKanji() {
        return uketori4NameKanji;
    }

    public void setUketori4NameKanji(String uketori4NameKanji) {
        this.uketori4NameKanji = uketori4NameKanji;
    }

    public String getUketori4Birth() {
        return uketori4Birth;
    }

    public void setUketori4Birth(String uketori4Birth) {
        this.uketori4Birth = uketori4Birth;
    }

    public String getUketori4AddressKanji() {
        return uketori4AddressKanji;
    }

    public void setUketori4AddressKanji(String uketori4AddressKanji) {
        this.uketori4AddressKanji = uketori4AddressKanji;
    }

    public String getUketori5NameKatakana() {
        return uketori5NameKatakana;
    }

    public void setUketori5NameKatakana(String uketori5NameKatakana) {
        this.uketori5NameKatakana = uketori5NameKatakana;
    }

    public String getUketori5NameKanji() {
        return uketori5NameKanji;
    }

    public void setUketori5NameKanji(String uketori5NameKanji) {
        this.uketori5NameKanji = uketori5NameKanji;
    }

    public String getUketori5Birth() {
        return uketori5Birth;
    }

    public void setUketori5Birth(String uketori5Birth) {
        this.uketori5Birth = uketori5Birth;
    }

    public String getUketori5AddressKanji() {
        return uketori5AddressKanji;
    }

    public void setUketori5AddressKanji(String uketori5AddressKanji) {
        this.uketori5AddressKanji = uketori5AddressKanji;
    }

    public String getHaraikomiHoho() {
        return haraikomiHoho;
    }

    public void setHaraikomiHoho(String haraikomiHoho) {
        this.haraikomiHoho = haraikomiHoho;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHaraikomiTukaCode() {
        return haraikomiTukaCode;
    }

    public void setHaraikomiTukaCode(String haraikomiTukaCode) {
        this.haraikomiTukaCode = haraikomiTukaCode;
    }

    public String getHaraikomiExRate() {
        return haraikomiExRate;
    }

    public void setHaraikomiExRate(String haraikomiExRate) {
        this.haraikomiExRate = haraikomiExRate;
    }

    public String getZenkiZennoHokenryo() {
        return zenkiZennoHokenryo;
    }

    public void setZenkiZennoHokenryo(String zenkiZennoHokenryo) {
        this.zenkiZennoHokenryo = zenkiZennoHokenryo;
    }

    public String getSyokaiHaraikomiHokenryo() {
        return syokaiHaraikomiHokenryo;
    }

    public void setSyokaiHaraikomiHokenryo(String syokaiHaraikomiHokenryo) {
        this.syokaiHaraikomiHokenryo = syokaiHaraikomiHokenryo;
    }

    public String getSyukeiyakuSyohinMei() {
        return syukeiyakuSyohinMei;
    }

    public void setSyukeiyakuSyohinMei(String syukeiyakuSyohinMei) {
        this.syukeiyakuSyohinMei = syukeiyakuSyohinMei;
    }

    public String getSyukeiyakuSyohinNo() {
        return syukeiyakuSyohinNo;
    }

    public void setSyukeiyakuSyohinNo(String syukeiyakuSyohinNo) {
        this.syukeiyakuSyohinNo = syukeiyakuSyohinNo;
    }

    public String getSyukeiyakuKikan() {
        return syukeiyakuKikan;
    }

    public void setSyukeiyakuKikan(String syukeiyakuKikan) {
        this.syukeiyakuKikan = syukeiyakuKikan;
    }

    public String getSyukeiyakuKikanKubun() {
        return syukeiyakuKikanKubun;
    }

    public void setSyukeiyakuKikanKubun(String syukeiyakuKikanKubun) {
        this.syukeiyakuKikanKubun = syukeiyakuKikanKubun;
    }

    public String getSyukeiyakuHokenryoHaraikomiKikan() {
        return syukeiyakuHokenryoHaraikomiKikan;
    }

    public void setSyukeiyakuHokenryoHaraikomiKikan(String syukeiyakuHokenryoHaraikomiKikan) {
        this.syukeiyakuHokenryoHaraikomiKikan = syukeiyakuHokenryoHaraikomiKikan;
    }

    public String getSyukeiyakuHokenryoHaraikomiKikanKubun() {
        return syukeiyakuHokenryoHaraikomiKikanKubun;
    }

    public void setSyukeiyakuHokenryoHaraikomiKikanKubun(String syukeiyakuHokenryoHaraikomiKikanKubun) {
        this.syukeiyakuHokenryoHaraikomiKikanKubun = syukeiyakuHokenryoHaraikomiKikanKubun;
    }

    public String getSyukeiyakuHokenNenkinKingaku() {
        return syukeiyakuHokenNenkinKingaku;
    }

    public void setSyukeiyakuHokenNenkinKingaku(String syukeiyakuHokenNenkinKingaku) {
        this.syukeiyakuHokenNenkinKingaku = syukeiyakuHokenNenkinKingaku;
    }

    public String getTuyotukaCode() {
        return tuyotukaCode;
    }

    public void setTuyotukaCode(String tuyotukaCode) {
        this.tuyotukaCode = tuyotukaCode;
    }

    public String getTuyotukaExRate() {
        return tuyotukaExRate;
    }

    public void setTuyotukaExRate(String tuyotukaExRate) {
        this.tuyotukaExRate = tuyotukaExRate;
    }

    public String getShindanKyufukinKibyo() {
        return shindanKyufukinKibyo;
    }

    public void setShindanKyufukinKibyo(String shindanKyufukinKibyo) {
        this.shindanKyufukinKibyo = shindanKyufukinKibyo;
    }

    public String getShindanKyufukinYokaigo() {
        return shindanKyufukinYokaigo;
    }

    public void setShindanKyufukinYokaigo(String shindanKyufukinYokaigo) {
        this.shindanKyufukinYokaigo = shindanKyufukinYokaigo;
    }

    public String getNyuinkyufukinTokuteiKibyo() {
        return nyuinkyufukinTokuteiKibyo;
    }

    public void setNyuinkyufukinTokuteiKibyo(String nyuinkyufukinTokuteiKibyo) {
        this.nyuinkyufukinTokuteiKibyo = nyuinkyufukinTokuteiKibyo;
    }

    public String getNyuinkyufukinTujyo() {
        return nyuinkyufukinTujyo;
    }

    public void setNyuinkyufukinTujyo(String nyuinkyufukinTujyo) {
        this.nyuinkyufukinTujyo = nyuinkyufukinTujyo;
    }

    public String getSyujyutukyufukinTokuteiKibyo() {
        return syujyutukyufukinTokuteiKibyo;
    }

    public void setSyujyutukyufukinTokuteiKibyo(String syujyutukyufukinTokuteiKibyo) {
        this.syujyutukyufukinTokuteiKibyo = syujyutukyufukinTokuteiKibyo;
    }

    public String getSyujyutukyufukinTujyo() {
        return syujyutukyufukinTujyo;
    }

    public void setSyujyutukyufukinTujyo(String syujyutukyufukinTujyo) {
        this.syujyutukyufukinTujyo = syujyutukyufukinTujyo;
    }

    public String getSeizonkyufukin() {
        return seizonkyufukin;
    }

    public void setSeizonkyufukin(String seizonkyufukin) {
        this.seizonkyufukin = seizonkyufukin;
    }

    public String getSueokiKikan() {
        return sueokiKikan;
    }

    public void setSueokiKikan(String sueokiKikan) {
        this.sueokiKikan = sueokiKikan;
    }

    public String getNenkannenkinShiharaiKaisu() {
        return nenkannenkinShiharaiKaisu;
    }

    public void setNenkannenkinShiharaiKaisu(String nenkannenkinShiharaiKaisu) {
        this.nenkannenkinShiharaiKaisu = nenkannenkinShiharaiKaisu;
    }

    @Override
    public String toString() {
        return "Data{" +
                "inscoCd='" + inscoCd + '\'' +
                ", gwId='" + gwId + '\'' +
                ", desDocNo='" + desDocNo + '\'' +
                ", applNo='" + applNo + '\'' +
                ", syokenNo='" + syokenNo + '\'' +
                ", caseManageNo='" + caseManageNo + '\'' +
                ", applSt='" + applSt + '\'' +
                ", desDocNoDate='" + desDocNoDate + '\'' +
                ", applDate='" + applDate + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", employeeNameKanji='" + employeeNameKanji + '\'' +
                ", toriatsukaiNameKanji='" + toriatsukaiNameKanji + '\'' +
                ", kyksNameKatakana='" + kyksNameKatakana + '\'' +
                ", kyksNameKanji='" + kyksNameKanji + '\'' +
                ", kyksBirth='" + kyksBirth + '\'' +
                ", kyksGender='" + kyksGender + '\'' +
                ", kyksAddressKanji='" + kyksAddressKanji + '\'' +
                ", kyksCompanyNameKatakana='" + kyksCompanyNameKatakana + '\'' +
                ", kyksCompanyNameKanji='" + kyksCompanyNameKanji + '\'' +
                ", hihoNameKatakana='" + hihoNameKatakana + '\'' +
                ", hihoNameKanji='" + hihoNameKanji + '\'' +
                ", hihoBirth='" + hihoBirth + '\'' +
                ", hihoAge='" + hihoAge + '\'' +
                ", hihoGender='" + hihoGender + '\'' +
                ", hihoAddressKanji='" + hihoAddressKanji + '\'' +
                ", hihoCompanyNameKatakana='" + hihoCompanyNameKatakana + '\'' +
                ", hihoCompanyNameKanji='" + hihoCompanyNameKanji + '\'' +
                ", uketori1NameKatakana='" + uketori1NameKatakana + '\'' +
                ", uketori1NameKanji='" + uketori1NameKanji + '\'' +
                ", uketori1Birth='" + uketori1Birth + '\'' +
                ", uketori1AddressKanji='" + uketori1AddressKanji + '\'' +
                ", uketori2NameKatakana='" + uketori2NameKatakana + '\'' +
                ", uketori2NameKanji='" + uketori2NameKanji + '\'' +
                ", uketori2Birth='" + uketori2Birth + '\'' +
                ", uketori2AddressKanji='" + uketori2AddressKanji + '\'' +
                ", uketori3NameKatakana='" + uketori3NameKatakana + '\'' +
                ", uketori3NameKanji='" + uketori3NameKanji + '\'' +
                ", uketori3Birth='" + uketori3Birth + '\'' +
                ", uketori3AddressKanji='" + uketori3AddressKanji + '\'' +
                ", uketori4NameKatakana='" + uketori4NameKatakana + '\'' +
                ", uketori4NameKanji='" + uketori4NameKanji + '\'' +
                ", uketori4Birth='" + uketori4Birth + '\'' +
                ", uketori4AddressKanji='" + uketori4AddressKanji + '\'' +
                ", uketori5NameKatakana='" + uketori5NameKatakana + '\'' +
                ", uketori5NameKanji='" + uketori5NameKanji + '\'' +
                ", uketori5Birth='" + uketori5Birth + '\'' +
                ", uketori5AddressKanji='" + uketori5AddressKanji + '\'' +
                ", haraikomiHoho='" + haraikomiHoho + '\'' +
                ", amount='" + amount + '\'' +
                ", haraikomiTukaCode='" + haraikomiTukaCode + '\'' +
                ", haraikomiExRate='" + haraikomiExRate + '\'' +
                ", zenkiZennoHokenryo='" + zenkiZennoHokenryo + '\'' +
                ", syokaiHaraikomiHokenryo='" + syokaiHaraikomiHokenryo + '\'' +
                ", syukeiyakuSyohinMei='" + syukeiyakuSyohinMei + '\'' +
                ", syukeiyakuSyohinNo='" + syukeiyakuSyohinNo + '\'' +
                ", syukeiyakuKikan='" + syukeiyakuKikan + '\'' +
                ", syukeiyakuKikanKubun='" + syukeiyakuKikanKubun + '\'' +
                ", syukeiyakuHokenryoHaraikomiKikan='" + syukeiyakuHokenryoHaraikomiKikan + '\'' +
                ", syukeiyakuHokenryoHaraikomiKikanKubun='" + syukeiyakuHokenryoHaraikomiKikanKubun + '\'' +
                ", syukeiyakuHokenNenkinKingaku='" + syukeiyakuHokenNenkinKingaku + '\'' +
                ", tuyotukaCode='" + tuyotukaCode + '\'' +
                ", tuyotukaExRate='" + tuyotukaExRate + '\'' +
                ", shindanKyufukinKibyo='" + shindanKyufukinKibyo + '\'' +
                ", shindanKyufukinYokaigo='" + shindanKyufukinYokaigo + '\'' +
                ", nyuinkyufukinTokuteiKibyo='" + nyuinkyufukinTokuteiKibyo + '\'' +
                ", nyuinkyufukinTujyo='" + nyuinkyufukinTujyo + '\'' +
                ", syujyutukyufukinTokuteiKibyo='" + syujyutukyufukinTokuteiKibyo + '\'' +
                ", syujyutukyufukinTujyo='" + syujyutukyufukinTujyo + '\'' +
                ", seizonkyufukin='" + seizonkyufukin + '\'' +
                ", sueokiKikan='" + sueokiKikan + '\'' +
                ", nenkannenkinShiharaiKaisu='" + nenkannenkinShiharaiKaisu + '\'' +
                '}';
    }
}
