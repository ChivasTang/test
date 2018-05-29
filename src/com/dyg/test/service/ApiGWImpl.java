package com.dyg.test.service;

import com.dyg.test.dto.ApiRequestData;
import com.dyg.test.dto.Data;
import com.dyg.test.dto.Header;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApiGWImpl implements ApiGW {
    private final long START=System.currentTimeMillis();

    @Override
    public void sendApiGW() {
        final String REGIST_INSERT_URL="https://smt7.hoken-gw.net/smtsso/fw/TAIYO-J1/tyoagent/PLTest.jsp?GW_UID=testtaiyo010&ApplSt=00";
//        final String REGIST_INSERT_STATUS="00";
//        final String REGIST_UPDATE_URL="https://smt7.hoken-gw.net/smtsso/fw/TAIYO-J1/tyoagent/PLTest.jsp?GW_UID=testtaiyo010&ApplSt=01";
//        final String REGIST_UPDATE_STATUS="01";

        //サーバへ接続するコネクション
        HttpURLConnection connection = null;

        //以下のように登録・更新用のURLを設定する。（REGIST_UPDATE_URLはAPI接続URLを定数定義）
        //https://smt7.hoken-gw.net/smtsso/fw/TAIYO-J1/tyoagent/PLTest.jsp?GW_UID=testtaiyo010
        URL url;
        try {
            url = new URL(REGIST_INSERT_URL);
            //接続用HttpURLConnectionオブジェクト作成
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //(1)送信時の通信仕様を設定
            // HTTPメソッドはPOSTを指定します。
            connection.setRequestMethod("POST");
            connection.setInstanceFollowRedirects(false);
            //(2)送信時のHTTPヘッダ部を設定
            //内容種類：Content-Type:application/xml
            //文字セット：charset=UTF-8
            //＜設定イメージ＞
            // Content-Type:application/xml;charset=UTF-8
            connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // (3)HTTPボディ部情報作成
        //標準出力にログを吐く
        System.out.println("<------------------------送信電文確認 START ------------------------>");
        //HTTPボディー部に設定する情報を作成します。
        //詳細はガイドラインの「別紙 Webファイル転送API送信応答仕様書」を参照。
        String httpBody = getRegistReqBody();
        System.out.println("<-------------------------送信電文確認 END ------------------------->");

        System.out.println(START);
        System.out.println(System.currentTimeMillis());
        System.out.println("xml作成時間（ミリ秒）："+(System.currentTimeMillis()-START));


        //4.データ送信
        //文字コードをUTF-8に設定
        DataOutputStream os;
        int iResponseCode = 0;
        String result="";
        try {
            os=new DataOutputStream(Objects.requireNonNull(connection).getOutputStream());
            os.write(httpBody.getBytes("UTF-8"));
            //タイムアウト設定（ミリ秒）
            connection.setReadTimeout(10000);
            // 5.結果コードの取得
            //結果コードを取得
            iResponseCode=connection.getResponseCode();
            if(iResponseCode==HttpURLConnection.HTTP_OK){
                StringBuilder resultBuilder=new StringBuilder();
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                //レスポンスのデータを読込
                String line;
                while ((line=br.readLine())!=null){
                    resultBuilder.append(line);
                }
                result=resultBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //標準出力にログを吐く
        System.out.println("レスポンスのデータは下記です。");
        System.out.println(iResponseCode);
        System.out.println(result);
    }

    @Override
    public void sendApiGW(String desDocNo) {

    }

    private static String getRegistReqBody(){
        //ApiRequestDataオブジェクトを新規作成
        ApiRequestData apiRequestData=new ApiRequestData();

        //リクエストデータの作成
        Data data = new Data();

        //ボディー情報を設定
        data.setInscoCd("保険会社コード");
        data.setGwId("共同GW利用ID");
        data.setDesDocNo("設計書番号");

        data.setApplSt("ステータス");
        data.setApplNo("申込書番号");
        data.setSyokenNo("証券番号");
        data.setCaseManageNo("案件管理番号");
        data.setDesDocNoDate("設計書作成日");
        data.setApplDate("申込日");
        data.setBranchCode("担当店番");
        data.setEmployeeNameKanji("募集人名-漢字");
        data.setToriatsukaiNameKanji("取扱承認者-漢字");

        data.setKyksNameKatakana("半角カナ");
        data.setKyksNameKanji("漢字");
        data.setKyksBirth("生年月日");
        data.setKyksGender("性別");
        data.setKyksAddressKanji("住所-漢字");
        data.setKyksCompanyNameKatakana("名称-半角カナ");
        data.setKyksCompanyNameKanji("名称-漢字");

        data.setHihoNameKatakana("半角カナ");
        data.setHihoNameKanji("漢字");
        data.setHihoBirth("生年月日");
        data.setHihoAge("年齢");
        data.setHihoGender("性別");
        data.setHihoAddressKanji("住所-漢字");

        data.setHihoCompanyNameKatakana("名称-半角カナ");
        data.setHihoCompanyNameKanji("名称-漢字");

        data.setUketori1NameKatakana("半角カナ1");
        data.setUketori1NameKanji("漢字1");
        data.setUketori1Birth("生年月日1");
        data.setUketori1AddressKanji("住所-漢字1");

        data.setUketori2NameKatakana("半角カナ2");
        data.setUketori2NameKanji("漢字2");
        data.setUketori2Birth("生年月日2");
        data.setUketori2AddressKanji("住所-漢字2");

        data.setUketori3NameKatakana("半角カナ3");
        data.setUketori3NameKanji("漢字3");
        data.setUketori3Birth("生年月日3");
        data.setUketori3AddressKanji("住所-漢字3");

        data.setUketori4NameKatakana("半角カナ4");
        data.setUketori4NameKanji("漢字4");
        data.setUketori4Birth("生年月日4");
        data.setUketori4AddressKanji("住所-漢字4");

        data.setUketori5NameKatakana("半角カナ5");
        data.setUketori5NameKanji("漢字5");
        data.setUketori5Birth("生年月日5");
        data.setUketori5AddressKanji("住所-漢字5");

        data.setHaraikomiHoho("保険料払込方法");

        data.setAmount("保険料");
        data.setHaraikomiTukaCode("通貨コード");
        data.setHaraikomiExRate("為替レート");
        data.setZenkiZennoHokenryo("全期前納保険料");
        data.setSyokaiHaraikomiHokenryo("初回払込金額");
        data.setSyukeiyakuSyohinMei("主契約商品名-漢字");
        data.setSyukeiyakuSyohinNo("主契約商品番号");
        data.setSyukeiyakuKikan("主契約保険期間");
        data.setSyukeiyakuKikanKubun("主契約保険期間区分");
        data.setSyukeiyakuHokenryoHaraikomiKikan("主契約保険料払込期間");
        data.setSyukeiyakuHokenryoHaraikomiKikanKubun("主契約保険料払込期間区分");

        data.setSyukeiyakuHokenNenkinKingaku("保険金額-年金金額");
        data.setTuyotukaCode("通貨コード");
        data.setTuyotukaExRate("為替レート");

        data.setShindanKyufukinKibyo("診断給付金-疾病");
        data.setShindanKyufukinYokaigo("診断給付金-要介護");
        data.setNyuinkyufukinTokuteiKibyo("入院給付金-特定疾病");
        data.setNyuinkyufukinTujyo("入院給付金-通常");
        data.setSyujyutukyufukinTokuteiKibyo("手術給付金-特定疾病");
        data.setSyujyutukyufukinTujyo("手術給付金-通常");
        data.setSeizonkyufukin("生存給付金");
        data.setSueokiKikan("据置期間");
        data.setNenkannenkinShiharaiKaisu("年間年金支払回数");
        apiRequestData.setData(data);

        //ヘッダの情報を設定
        Header header = new Header();
        //ヘッダーをapiRequestDataに設定
        //認証キーを設定
        header.setAuthKey(header.getAuthKey());
        //ファイルIDを設定
//        header.setFileId(header.getFileId());
        //送信元識別子を設定
//        header.setTransmitFromCd(header.getTransmitFromCd());
        //送信時刻を設定
        header.setTime(header.getTime());
        //ヘッダをリクエストデータに追加
        apiRequestData.setHeader(header);

        System.out.println(apiRequestData.toString());
        return createXMLString(apiRequestData);
    }

    private static DocumentBuilder getDocumentBuilder() {
        //DocumentBuilderFactoryオブジェクトを作成
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //DocumentBuilderオブジェクトを作成
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    private static String createXMLString(ApiRequestData requestData){
        final String EMPTY = "";
        ArrayList<Element> list = new ArrayList<>();
        DocumentBuilder builder = getDocumentBuilder();
        Document document = builder.newDocument();

        //dataを加工--必須パラメータ--
        Element data = document.createElement("Data");

        Element inscoCd = document.createElement("保険会社コード");
        inscoCd.setTextContent(requestData.getData().getInscoCd());
        data.appendChild(inscoCd);

        Element gwId = document.createElement("共同GW利用ID");
        gwId.setTextContent(requestData.getData().getGwId());
        data.appendChild(gwId);

        Element desDocNo = document.createElement("設計書番号");
        desDocNo.setTextContent(requestData.getData().getDesDocNo());
        data.appendChild(desDocNo);

        //dataを加工--普通パラメータ--
        // ステータス  ステータス  半角数字  2  以下の値を設定""00"" 未承認、""01"" 承認済み
        // （新規データ還元時設定必須）
        if (!requestData.getData().getApplSt().isEmpty() || !requestData.getData().getApplSt().equals(EMPTY)) {
            Element applSt = document.createElement("ステータス");
            applSt.setTextContent(requestData.getData().getApplSt());
            data.appendChild(applSt);
        }

        // 申込書番号  申込書番号  半角文字  20  申込書番号を設定
        //（新規データ還元時設定必須）
        if (!requestData.getData().getApplNo().isEmpty() || !requestData.getData().getApplNo().equals(EMPTY)) {
            Element applNo = document.createElement("申込書番号");
            applNo.setTextContent(requestData.getData().getApplNo());
            data.appendChild(applNo);
        }

        // 証券番号  証券番号  半角文字  23  証券番号を設定
        if (!requestData.getData().getSyokenNo().isEmpty() || !requestData.getData().getSyokenNo().equals(EMPTY)) {
            Element syokenNo = document.createElement("証券番号");
            syokenNo.setTextContent(requestData.getData().getSyokenNo());
            data.appendChild(syokenNo);
        }

        // 案件管理番号  案件管理番号  半角文字  15  代理店の案件管理番号を設定
        if (!requestData.getData().getCaseManageNo().isEmpty() || !requestData.getData().getCaseManageNo().equals(EMPTY)) {
            Element caseManageNo = document.createElement("案件管理番号");
            caseManageNo.setTextContent(requestData.getData().getCaseManageNo());
            data.appendChild(caseManageNo);
        }

        // 設計書作成日  設計書作成日  半角数字  8  YYYYMMDD：Web上で設計書が作成された年月日を設定(年・月・日 各々前ZERO)
        //（新規データ還元時設定必須）
        if (!requestData.getData().getDesDocNoDate().isEmpty() || !requestData.getData().getDesDocNoDate().equals(EMPTY)) {
            Element desDocNoDate = document.createElement("設計書作成日");
            desDocNoDate.setTextContent(requestData.getData().getDesDocNoDate());
            data.appendChild(desDocNoDate);
        }

        // 申込日  申込日  半角数字  8  YYYYMMDD：Web上で申込がされた年月日を設定(年・月・日 各々前ZERO)
        //（新規データ還元時設定必須）
        if (!requestData.getData().getApplDate().isEmpty() || !requestData.getData().getApplDate().equals(EMPTY)) {
            Element applDate = document.createElement("申込日");
            applDate.setTextContent(requestData.getData().getApplDate());
            data.appendChild(applDate);
        }

        // 取扱  (グループ名)  -  -
        // 担当店番  店番  半角文字  9  代理店内において所属する部門を表すコードを設定
        // 担当者  (グループ名)  -  -
        // 募集人名-漢字  募集人名（漢字）  全半角文字  60  募集人名（漢字）を設定
        // 取扱承認者-漢字  取扱承認者（漢字）  全半角文字  60  取扱承認者名（漢字）を設定
        Element toriatsukai = null;
        Element branchCode = null;
        Element tantoSya = null;
        Element employeeNameKanji = null;
        Element toriatsukaiNameKanji = null;

        if (!requestData.getData().getBranchCode().isEmpty() || !requestData.getData().getBranchCode().equals(EMPTY)) {
            branchCode = document.createElement("担当店番");
            branchCode.setTextContent(requestData.getData().getBranchCode());
        }
        if (!requestData.getData().getEmployeeNameKanji().isEmpty() || !requestData.getData().getEmployeeNameKanji().equals(EMPTY)) {
            employeeNameKanji = document.createElement("募集人名-漢字");
            employeeNameKanji.setTextContent(requestData.getData().getEmployeeNameKanji());
        }
        if (!requestData.getData().getToriatsukaiNameKanji().isEmpty() || !requestData.getData().getToriatsukaiNameKanji().equals(EMPTY)) {
            toriatsukaiNameKanji = document.createElement("取扱承認者-漢字");
            toriatsukaiNameKanji.setTextContent(requestData.getData().getToriatsukaiNameKanji());
        }
        if (employeeNameKanji != null || toriatsukaiNameKanji != null) {
            tantoSya = document.createElement("担当者");
            if (employeeNameKanji != null) {
                tantoSya.appendChild(employeeNameKanji);
            }
            if (toriatsukaiNameKanji != null) {
                tantoSya.appendChild(toriatsukaiNameKanji);
            }
        }
        if (branchCode != null || tantoSya != null) {
            toriatsukai = document.createElement("取扱");
            if (branchCode != null) {
                toriatsukai.appendChild(branchCode);
            }
            if (tantoSya != null) {
                toriatsukai.appendChild(tantoSya);
            }
        }
        if (toriatsukai != null) {
            data.appendChild(toriatsukai);
        }

        // 契約者  (グループ名)  -  -
        // 氏名  (グループ名)  -  -
        // 半角カナ  契約者名（半角カナ）  半角文字  120  契約者名（半角カナ）を設定
        // 漢字  契約者名（漢字）  全半角文字  60  契約者名（漢字）を設定
        Element kyks;
        Element kyksName;
        Element kyksNameKatakana = null;
        Element kyksNameKanji = null;
        Element kyksBirth = null;
        Element kyksGender = null;
        Element kyksAddressKanji = null;
        Element kyksKinmusaki;
        Element kyksCompanyNameKatakana = null;
        Element kyksCompanyNameKanji = null;

        if (!requestData.getData().getKyksNameKatakana().isEmpty() || !requestData.getData().getKyksNameKatakana().equals(EMPTY)) {
            kyksNameKatakana = document.createElement("半角カナ");
            kyksNameKatakana.setTextContent(requestData.getData().getKyksNameKatakana());
        }
        if (!requestData.getData().getKyksNameKanji().isEmpty() || !requestData.getData().getKyksNameKanji().equals(EMPTY)) {
            kyksNameKanji = document.createElement("漢字");
            kyksNameKanji.setTextContent(requestData.getData().getKyksNameKanji());
        }

        list.add(kyksNameKatakana);
        list.add(kyksNameKanji);
        kyksName = getUpperElement("氏名", document, list);
        list.clear();

        // 生年月日  契約者生年月日  半角数字  8  YYYYMMDD：契約者の生年月日を設定(年・月・日 各々前ZERO)
        if (!requestData.getData().getKyksBirth().isEmpty() || !requestData.getData().getKyksBirth().equals(EMPTY)) {
            kyksBirth = document.createElement("生年月日");
            kyksBirth.setTextContent(requestData.getData().getKyksBirth());
        }
        // 性別  契約者性別  半角文字  1  コード値は1700バイト（契約者性別）準拠とします
        if (!requestData.getData().getKyksGender().isEmpty() || !requestData.getData().getKyksGender().isEmpty()) {
            kyksGender = document.createElement("性別");
            kyksGender.setTextContent(requestData.getData().getKyksGender());
        }
        // 住所-漢字  契約者住所（漢字）  全半角文字  120  契約者住所（漢字）を設定
        if (!requestData.getData().getKyksAddressKanji().isEmpty() || !requestData.getData().getKyksAddressKanji().equals(EMPTY)) {
            kyksAddressKanji = document.createElement("住所-漢字");
            kyksAddressKanji.setTextContent(requestData.getData().getKyksAddressKanji());
        }
        // 勤務先名  (グループ名)  -  -
        // 名称-半角カナ  契約者勤務先（半角カナ）  半角文字  50  契約者の勤務先及び勤務先所属（半角カナ）を設定
        // 名称-漢字  契約者勤務先（漢字）  全半角文字  60  契約者の勤務先及び勤務先所属（漢字）を設定
        if (!requestData.getData().getKyksCompanyNameKatakana().isEmpty() || !requestData.getData().getKyksCompanyNameKatakana().equals(EMPTY)) {
            kyksCompanyNameKatakana = document.createElement("名称-半角カナ");
            kyksCompanyNameKatakana.setTextContent(requestData.getData().getKyksCompanyNameKatakana());
        }
        if (!requestData.getData().getKyksCompanyNameKanji().isEmpty() || !requestData.getData().getKyksCompanyNameKanji().equals(EMPTY)) {
            kyksCompanyNameKanji = document.createElement("名称-漢字");
            kyksCompanyNameKanji.setTextContent(requestData.getData().getKyksCompanyNameKanji());
        }
        list.add(kyksCompanyNameKatakana);
        list.add(kyksCompanyNameKanji);
        kyksKinmusaki = getUpperElement("勤務先名", document, list);
        list.clear();

        list.add(kyksName);
        list.add(kyksBirth);
        list.add(kyksGender);
        list.add(kyksAddressKanji);
        list.add(kyksKinmusaki);
        kyks = getUpperElement("契約者", document, list);
        list.clear();
        if (kyks != null) {
            data.appendChild(kyks);
        }


        // 被保険者  (グループ名)  -  -  契被同一の場合、契約者の同項目と同一値を設定
        // 氏名  (グループ名)  -  -
        // 半角カナ  被保険者名（半角カナ）  半角文字  120  被保険者名（半角カナ）を設定
        // 漢字  被保険者名（漢字）  全半角文字  60  被保険者名（漢字）を設定
        // 生年月日  被保険者生年月日  半角数字  8  YYYYMMDD：被保険者の生年月日を設定(年・月・日 各々前ZERO)
        // 年齢  被保険者年齢  半角数字  3  被保険者の年齢を設定（保険料算出基準年齢）
        // 性別  被保険者性別  半角文字  1  コード値は1700バイト（被保険者性別）準拠とします
        // 住所-漢字  被保険者住所（漢字）  全半角文字  120  被保険者住所（漢字）を設定
        // 勤務先名  (グループ名)  -  -
        // 名称-半角カナ  被保険者勤務先（半角カナ）  半角文字  50  被保険者の勤務先及び勤務先所属（半角カナ）を設定
        // 名称-漢字  被保険者勤務先（漢字）  全半角文字  60  被保険者の勤務先及び勤務先所属（漢字）を設定
        Element hiho;
        Element hihoName = null;
        Element hihoNameKatakana = null;
        Element hihoNameKanji = null;
        Element hihoBirth = null;
        Element hihoAge = null;
        Element hihoGender = null;
        Element hihoAddressKanji = null;
        Element hihoKinmusaki;
        Element hihoCompanyNameKatakana = null;
        Element hihoCompanyNameKanji = null;

        if (!requestData.getData().getHihoNameKatakana().isEmpty() || !requestData.getData().getHihoNameKatakana().equals(EMPTY)) {
            hihoNameKatakana = document.createElement("半角カナ");
            hihoNameKatakana.setTextContent(requestData.getData().getHihoNameKatakana());
        }
        if (!requestData.getData().getHihoNameKanji().isEmpty() || !requestData.getData().getHihoNameKanji().equals(EMPTY)) {
            hihoNameKanji = document.createElement("漢字");
            hihoNameKanji.setTextContent(requestData.getData().getHihoNameKanji());
        }
        if (hihoNameKatakana != null || hihoNameKanji != null) {
            hihoName = document.createElement("氏名");
            if (hihoNameKatakana != null) {
                hihoName.appendChild(hihoNameKatakana);
            }
            if (hihoNameKanji != null) {
                hihoName.appendChild(hihoNameKanji);
            }
        }

        if (!requestData.getData().getHihoAge().isEmpty() || !requestData.getData().getHihoAge().equals(EMPTY)) {
            hihoAge = document.createElement("年齢");
            hihoAge.setTextContent(requestData.getData().getHihoAge());
        }
        if (!requestData.getData().getHihoBirth().isEmpty() || !requestData.getData().getHihoBirth().equals(EMPTY)) {
            hihoBirth = document.createElement("生年月日");
            hihoBirth.setTextContent(requestData.getData().getHihoBirth());
        }
        if (!requestData.getData().getHihoGender().isEmpty() || !requestData.getData().getHihoGender().isEmpty()) {
            hihoGender = document.createElement("性別");
            hihoGender.setTextContent(requestData.getData().getHihoGender());
        }

        if (!requestData.getData().getHihoAddressKanji().isEmpty() || !requestData.getData().getHihoAddressKanji().equals(EMPTY)) {
            hihoAddressKanji = document.createElement("住所-漢字");
            hihoAddressKanji.setTextContent(requestData.getData().getHihoAddressKanji());
        }

        if (!requestData.getData().getHihoCompanyNameKatakana().isEmpty() || !requestData.getData().getHihoCompanyNameKatakana().equals(EMPTY)) {
            hihoCompanyNameKatakana = document.createElement("名称-半角カナ");
            hihoCompanyNameKatakana.setTextContent(requestData.getData().getHihoCompanyNameKatakana());
        }
        if (!requestData.getData().getHihoCompanyNameKanji().isEmpty() || !requestData.getData().getHihoCompanyNameKanji().equals(EMPTY)) {
            hihoCompanyNameKanji = document.createElement("名称-漢字");
            hihoCompanyNameKanji.setTextContent(requestData.getData().getHihoCompanyNameKanji());
        }
        list.add(hihoCompanyNameKatakana);
        list.add(hihoCompanyNameKanji);
        hihoKinmusaki = getUpperElement("勤務先名", document, list);
        list.clear();

        list.add(hihoName);
        list.add(hihoBirth);
        list.add(hihoAge);
        list.add(hihoGender);
        list.add(hihoAddressKanji);
        list.add(hihoKinmusaki);
        hiho = getUpperElement("被保険者", document, list);
        list.clear();
        if (hiho != null) {
            data.appendChild(hiho);
        }

        // 受取人  (グループ名)  -  -
        // 受取人1  (グループ名)  -  -
        // 氏名  (グループ名)  -  -
        // 半角カナ  受取人名１（半角カナ）  半角文字  120  受取人１の氏名（半角カナ）を設定
        // 漢字  受取人名１（漢字）  全半角文字  60  受取人１の氏名（漢字）を設定
        // 生年月日  受取人生年月日１  半角数字  8  YYYYMMDD：受取人１の生年月日を設定(年・月・日 各々前ZERO)
        // 住所-漢字  受取人住所１（漢字）  全半角文字  120  受取人１の住所（漢字）を設定
        Element uketori;
        Element uketori1;
        Element uketori1Name;
        Element uketori1NameKatakana = null;
        Element uketori1NameKanji = null;
        Element uketori1Birth = null;
        Element uketori1AddressKanji = null;

        if (!requestData.getData().getUketori1NameKatakana().isEmpty() || !requestData.getData().getUketori1NameKatakana().equals(EMPTY)) {
            uketori1NameKatakana = document.createElement("半角カナ");
            uketori1NameKatakana.setTextContent(requestData.getData().getUketori1NameKatakana());
        }

        if (!requestData.getData().getUketori1NameKanji().isEmpty() || !requestData.getData().getUketori1NameKanji().equals(EMPTY)) {
            uketori1NameKanji = document.createElement("漢字");
            uketori1NameKanji.setTextContent(requestData.getData().getUketori1NameKanji());
        }

        list.add(uketori1NameKatakana);
        list.add(uketori1NameKanji);
        uketori1Name = getUpperElement("氏名", document, list);
        list.clear();

        if (!requestData.getData().getUketori1Birth().isEmpty() || !requestData.getData().getUketori1Birth().equals(EMPTY)) {
            uketori1Birth = document.createElement("生年月日");
            uketori1Birth.setTextContent(requestData.getData().getUketori1Birth());
        }
        if (!requestData.getData().getUketori1AddressKanji().isEmpty() || !requestData.getData().getUketori1AddressKanji().equals(EMPTY)) {
            uketori1AddressKanji = document.createElement("住所-漢字");
            uketori1AddressKanji.setTextContent(requestData.getData().getUketori1AddressKanji());
        }

        list.add(uketori1Name);
        list.add(uketori1Birth);
        list.add(uketori1AddressKanji);
        uketori1 = getUpperElement("受取人1", document, list);
        list.clear();

        Element uketori2;
        Element uketori2Name;
        Element uketori2NameKatakana = null;
        Element uketori2NameKanji = null;
        Element uketori2Birth = null;
        Element uketori2AddressKanji = null;

        if (!requestData.getData().getUketori2NameKatakana().isEmpty() || !requestData.getData().getUketori2NameKatakana().equals(EMPTY)) {
            uketori2NameKatakana = document.createElement("半角カナ");
            uketori2NameKatakana.setTextContent(requestData.getData().getUketori2NameKatakana());
        }

        if (!requestData.getData().getUketori2NameKanji().isEmpty() || !requestData.getData().getUketori2NameKanji().equals(EMPTY)) {
            uketori2NameKanji = document.createElement("漢字");
            uketori2NameKanji.setTextContent(requestData.getData().getUketori2NameKanji());
        }

        list.add(uketori2NameKatakana);
        list.add(uketori2NameKanji);
        uketori2Name = getUpperElement("氏名", document, list);
        list.clear();

        if (!requestData.getData().getUketori2Birth().isEmpty() || !requestData.getData().getUketori2Birth().equals(EMPTY)) {
            uketori2Birth = document.createElement("生年月日");
            uketori2Birth.setTextContent(requestData.getData().getUketori2Birth());
        }
        if (!requestData.getData().getUketori2AddressKanji().isEmpty() || !requestData.getData().getUketori2AddressKanji().equals(EMPTY)) {
            uketori2AddressKanji = document.createElement("住所-漢字");
            uketori2AddressKanji.setTextContent(requestData.getData().getUketori2AddressKanji());
        }

        list.add(uketori2Name);
        list.add(uketori2Birth);
        list.add(uketori2AddressKanji);
        uketori2 = getUpperElement("受取人2", document, list);
        list.clear();

        Element uketori3;
        Element uketori3Name;
        Element uketori3NameKatakana = null;
        Element uketori3NameKanji = null;
        Element uketori3Birth = null;
        Element uketori3AddressKanji = null;

        if (!requestData.getData().getUketori3NameKatakana().isEmpty() || !requestData.getData().getUketori3NameKatakana().equals(EMPTY)) {
            uketori3NameKatakana = document.createElement("半角カナ");
            uketori3NameKatakana.setTextContent(requestData.getData().getUketori3NameKatakana());
        }

        if (!requestData.getData().getUketori3NameKanji().isEmpty() || !requestData.getData().getUketori3NameKanji().equals(EMPTY)) {
            uketori3NameKanji = document.createElement("漢字");
            uketori3NameKanji.setTextContent(requestData.getData().getUketori3NameKanji());
        }

        list.add(uketori3NameKatakana);
        list.add(uketori3NameKanji);
        uketori3Name = getUpperElement("氏名", document, list);
        list.clear();

        if (!requestData.getData().getUketori3Birth().isEmpty() || !requestData.getData().getUketori3Birth().equals(EMPTY)) {
            uketori3Birth = document.createElement("生年月日");
            uketori3Birth.setTextContent(requestData.getData().getUketori3Birth());
        }
        if (!requestData.getData().getUketori3AddressKanji().isEmpty() || !requestData.getData().getUketori3AddressKanji().equals(EMPTY)) {
            uketori3AddressKanji = document.createElement("住所-漢字");
            uketori3AddressKanji.setTextContent(requestData.getData().getUketori3AddressKanji());
        }

        list.add(uketori3Name);
        list.add(uketori3Birth);
        list.add(uketori3AddressKanji);
        uketori3 = getUpperElement("受取人3", document, list);
        list.clear();

        Element uketori4;
        Element uketori4Name;
        Element uketori4NameKatakana = null;
        Element uketori4NameKanji = null;
        Element uketori4Birth = null;
        Element uketori4AddressKanji = null;

        if (!requestData.getData().getUketori4NameKatakana().isEmpty() || !requestData.getData().getUketori4NameKatakana().equals(EMPTY)) {
            uketori4NameKatakana = document.createElement("半角カナ");
            uketori4NameKatakana.setTextContent(requestData.getData().getUketori4NameKatakana());
        }

        if (!requestData.getData().getUketori4NameKanji().isEmpty() || !requestData.getData().getUketori4NameKanji().equals(EMPTY)) {
            uketori4NameKanji = document.createElement("漢字");
            uketori4NameKanji.setTextContent(requestData.getData().getUketori4NameKanji());
        }

        list.add(uketori4NameKatakana);
        list.add(uketori4NameKanji);
        uketori4Name = getUpperElement("氏名", document, list);
        list.clear();

        if (!requestData.getData().getUketori4Birth().isEmpty() || !requestData.getData().getUketori4Birth().equals(EMPTY)) {
            uketori4Birth = document.createElement("生年月日");
            uketori4Birth.setTextContent(requestData.getData().getUketori4Birth());
        }
        if (!requestData.getData().getUketori4AddressKanji().isEmpty() || !requestData.getData().getUketori4AddressKanji().equals(EMPTY)) {
            uketori4AddressKanji = document.createElement("住所-漢字");
            uketori4AddressKanji.setTextContent(requestData.getData().getUketori4AddressKanji());
        }

        list.add(uketori4Name);
        list.add(uketori4Birth);
        list.add(uketori4AddressKanji);
        uketori4 = getUpperElement("受取人4", document, list);
        list.clear();

        Element uketori5;
        Element uketori5Name;
        Element uketori5NameKatakana = null;
        Element uketori5NameKanji = null;
        Element uketori5Birth = null;
        Element uketori5AddressKanji = null;

        if (!requestData.getData().getUketori5NameKatakana().isEmpty() || !requestData.getData().getUketori5NameKatakana().equals(EMPTY)) {
            uketori5NameKatakana = document.createElement("半角カナ");
            uketori5NameKatakana.setTextContent(requestData.getData().getUketori5NameKatakana());
        }

        if (!requestData.getData().getUketori5NameKanji().isEmpty() || !requestData.getData().getUketori5NameKanji().equals(EMPTY)) {
            uketori5NameKanji = document.createElement("漢字");
            uketori5NameKanji.setTextContent(requestData.getData().getUketori5NameKanji());
        }

        list.add(uketori5NameKatakana);
        list.add(uketori5NameKanji);
        uketori5Name = getUpperElement("氏名", document, list);
        list.clear();

        if (!requestData.getData().getUketori5Birth().isEmpty() || !requestData.getData().getUketori5Birth().equals(EMPTY)) {
            uketori5Birth = document.createElement("生年月日");
            uketori5Birth.setTextContent(requestData.getData().getUketori5Birth());
        }
        if (!requestData.getData().getUketori5AddressKanji().isEmpty() || !requestData.getData().getUketori5AddressKanji().equals(EMPTY)) {
            uketori5AddressKanji = document.createElement("住所-漢字");
            uketori5AddressKanji.setTextContent(requestData.getData().getUketori5AddressKanji());
        }

        list.add(uketori5Name);
        list.add(uketori5Birth);
        list.add(uketori5AddressKanji);
        uketori5 = getUpperElement("受取人5", document, list);
        list.clear();

        list.add(uketori1);
        list.add(uketori2);
        list.add(uketori3);
        list.add(uketori4);
        list.add(uketori5);
        uketori = getUpperElement("受取人", document, list);
        list.clear();

        if (uketori != null) {
            data.appendChild(uketori);
        }

        // 契約内容  (グループ名)  -  -
        Element keiyakuNaiyo;
        // 保険料払込方法  保険料払込方法  半角文字  1  コード値は1700バイト（払込方法）準拠とします
        //（新規データ還元時設定必須）
        Element haraikomiHoho = null;
        if (!requestData.getData().getHaraikomiHoho().isEmpty() || !requestData.getData().getHaraikomiHoho().equals(EMPTY)) {
            haraikomiHoho = document.createElement("保険料払込方法");
            haraikomiHoho.setTextContent(requestData.getData().getHaraikomiHoho());
        }
        // 合計保険料  (グループ名)  -  -
        Element gokeiHokenryo;
        // 保険料  払込金額  半角数字  11  払込金額の値を設定。
        //一時払の場合は一時払保険料を設定
        //平準払の場合は初回保険料を設定
        //外貨の場合は補助通貨単位にて設定
        //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
        // ※保険料とは主契約保険料＋特約保険料＋前納保険料を表します。
        //（新規データ還元時設定必須）
        Element amount = null;
        if (!requestData.getData().getAmount().isEmpty() || !requestData.getData().getAmount().equals(EMPTY)) {
            amount = document.createElement("保険料");
            amount.setTextContent(requestData.getData().getAmount());
        }
        // 通貨コード  通貨コード（払込金）  半角文字  3  払込金の通貨コードを設定
        // コード値は生保汎用版手数料データ（通貨コード）準拠とします。
        // （新規データ還元時設定必須）
        Element haraikomiTukaCode = null;
        if (!requestData.getData().getHaraikomiTukaCode().isEmpty() || !requestData.getData().getHaraikomiTukaCode().equals(EMPTY)) {
            haraikomiTukaCode = document.createElement("通貨コード");
            haraikomiTukaCode.setTextContent(requestData.getData().getHaraikomiTukaCode());
        }
        // 為替レート  為替レート（保険料）  半角数字  10  保険料等の円換算時の為替レートを設定
        // 支払金通貨が円の場合はZEROを設定
        //《整数部：前方7桁。小数部：後方3桁。左詰め後0設定。》
        // （例）103.50⇒103500 123.00⇒123000
        // ※保険料が外貨の商品については新規データ還元時設定必須
        Element haraikomiExRate = null;
        if (!requestData.getData().getHaraikomiExRate().isEmpty() || !requestData.getData().getHaraikomiExRate().equals(EMPTY)) {
            haraikomiExRate = document.createElement("為替レート");
            haraikomiExRate.setTextContent(requestData.getData().getHaraikomiExRate());
        }
        list.add(amount);
        list.add(haraikomiTukaCode);
        list.add(haraikomiExRate);
        gokeiHokenryo = getUpperElement("合計保険料", document, list);
        list.clear();
        // 全期前納保険料  全期前納保険料  半角数字  11  全期前納の場合の前納保険料を設定
        // 外貨建ての場合は補助通貨単位にて設定
        //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
        // ※一部前納の場合も設定
        Element zenkiZennoHokenryo = null;
        if (!requestData.getData().getZenkiZennoHokenryo().isEmpty() || !requestData.getData().getZenkiZennoHokenryo().equals(EMPTY)) {
            zenkiZennoHokenryo = document.createElement("全期前納保険料");
            zenkiZennoHokenryo.setTextContent(requestData.getData().getZenkiZennoHokenryo());
        }
        // 初回払込金額  初回払込保険料  半角数字  11  初回払込金額を設定
        // ※数回分まとめて保険料を払い込む場合、数回分の合計値を設定
        //外貨建ての場合は補助通貨単位にて設定
        //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
        // （新規データ還元時設定必須）
        Element syokaiHaraikomiHokenryo = null;
        if (!requestData.getData().getSyokaiHaraikomiHokenryo().isEmpty() || !requestData.getData().getSyokaiHaraikomiHokenryo().equals(EMPTY)) {
            syokaiHaraikomiHokenryo = document.createElement("初回払込金額");
            syokaiHaraikomiHokenryo.setTextContent(requestData.getData().getSyokaiHaraikomiHokenryo());
        }
        // 主契約商品名-漢字  主契約商品名（漢字）  全半角文字  120  保険商品名（漢字）（ペットネーム）を設定
        // ※ひらがな／カタカナ／英数字の設定可
        //（新規データ還元時設定必須）
        Element syukeiyakuSyohinMei = null;
        if (!requestData.getData().getSyukeiyakuSyohinMei().isEmpty() || !requestData.getData().getSyukeiyakuSyohinMei().equals(EMPTY)) {
            syukeiyakuSyohinMei = document.createElement("主契約商品名-漢字");
            syukeiyakuSyohinMei.setTextContent(requestData.getData().getSyukeiyakuSyohinMei());
        }
        // 主契約商品番号  主契約商品番号  半角文字  7  各保険会社が商品毎に付与する独自コードを設定
        // ※データ連携項目「主契約商品番号」と同じ値を設定
        // （新規データ還元時設定必須）
        Element syukeiyakuSyohinNo = null;
        if (!requestData.getData().getSyukeiyakuSyohinNo().isEmpty() || !requestData.getData().getSyukeiyakuSyohinNo().equals(EMPTY)) {
            syukeiyakuSyohinNo = document.createElement("主契約商品番号");
            syukeiyakuSyohinNo.setTextContent(requestData.getData().getSyukeiyakuSyohinNo());
        }
        // 主契約保険期間  主契約保険期間  半角文字  3  年満期の場合は、保険期間を設定（単位：年）
        // 歳満期の場合は、満期時年齢を設定
        // 終身の場合は、ZEROを設定
        //（新規データ還元時設定必須）
        Element syukeiyakuKikan = null;
        if (!requestData.getData().getSyukeiyakuKikan().isEmpty() || !requestData.getData().getSyukeiyakuKikan().equals(EMPTY)) {
            syukeiyakuKikan = document.createElement("主契約保険期間");
            syukeiyakuKikan.setTextContent(requestData.getData().getSyukeiyakuKikan());
        }
        // 主契約保険期間区分  主契約保険期間区分  半角文字  1  コード値は1700バイト（保険期間区分）準拠とします
        //（新規データ還元時設定必須）
        Element syukeiyakuKikanKubun = null;
        if (!requestData.getData().getSyukeiyakuKikanKubun().isEmpty() || !requestData.getData().getSyukeiyakuKikanKubun().equals(EMPTY)) {
            syukeiyakuKikanKubun = document.createElement("主契約保険期間区分");
            syukeiyakuKikanKubun.setTextContent(requestData.getData().getSyukeiyakuKikanKubun());
        }
        // 主契約保険料払込期間  主契約保険料払込期間  半角文字  3  年払込の場合は、保険期間を設定（単位：年）
        // 歳払込の場合は、払込終了時年齢を設定
        // 一時払込、終身払込の場合は、ZEROを設定
        Element syukeiyakuHokenryoHaraikomiKikan = null;
        if (!requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan().isEmpty() || !requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan().equals(EMPTY)) {
            syukeiyakuHokenryoHaraikomiKikan = document.createElement("主契約保険料払込期間");
            syukeiyakuHokenryoHaraikomiKikan.setTextContent(requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan());
        }
        // 主契約保険料払込期間区分  主契約保険料払込期間区分  半角文字  1  コード値は1700バイト（払込期間区分）準拠とします
        // ※一時払込の場合は初期値を設定
        Element syukeiyakuHokenryoHaraikomiKikanKubun = null;
        if (!requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun().isEmpty() || !requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun().equals(EMPTY)) {
            syukeiyakuHokenryoHaraikomiKikanKubun = document.createElement("主契約保険料払込期間区分");
            syukeiyakuHokenryoHaraikomiKikanKubun.setTextContent(requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun());
        }
        // 主契約保険金  (グループ名)  -  -
        Element syukeiyakuHokenkin;
        // 保険金額-年金金額  主契約保険金額／年金金額  半角数字  11  主契約保険金または年金金額を設定
        // 外貨建ての場合は、補助通貨単位にて設定
        // ※死亡保険金、満期保険金、年金年額等の商品の特性に応じた金額を設定
        //（例）12ドル34セント⇒123412ドル（0セント）⇒1200
        // （原則設定必須）
        Element syukeiyakuHokenNenkinKingaku = null;
        if (!requestData.getData().getSyukeiyakuHokenNenkinKingaku().isEmpty() || !requestData.getData().getSyukeiyakuHokenNenkinKingaku().equals(EMPTY)) {
            syukeiyakuHokenNenkinKingaku = document.createElement("保険金額-年金金額");
            syukeiyakuHokenNenkinKingaku.setTextContent(requestData.getData().getSyukeiyakuHokenNenkinKingaku());
        }
        // 通貨コード  通貨コード（運用通貨）  半角文字  3  運用通貨の通貨コードを設定
        // コード値は生保汎用版手数料データ（通貨コード）準拠とします。
        // （原則設定必須）
        Element tuyotukaCode = null;
        if (!requestData.getData().getTuyotukaCode().isEmpty() || !requestData.getData().getTuyotukaCode().equals(EMPTY)) {
            tuyotukaCode = document.createElement("通貨コード");
            tuyotukaCode.setTextContent(requestData.getData().getTuyotukaCode());
        }
        // 為替レート  為替レート（運用通貨）  半角数字  10  運用通貨の円換算時の為替レートを設定
        // 運用通貨が円の場合はZEROを設定
        //《整数部：前方7桁。小数部：後方3桁。左詰め後0設定。》
        // （例）103.50⇒103500 123.00⇒123000
        // ※運用通貨が外貨の商品については新規データ還元時設定必須
        Element tuyotukaExRate = null;
        if (!requestData.getData().getTuyotukaExRate().isEmpty() || !requestData.getData().getTuyotukaExRate().equals(EMPTY)) {
            tuyotukaExRate = document.createElement("為替レート");
            tuyotukaExRate.setTextContent(requestData.getData().getTuyotukaExRate());
        }

        list.add(syukeiyakuHokenNenkinKingaku);
        list.add(tuyotukaCode);
        list.add(tuyotukaExRate);
        syukeiyakuHokenkin = getUpperElement("主契約保険金", document, list);
        list.clear();

        // 給付金  (グループ名)  -  -
        Element kyufukin;
        // 診断給付金-疾病  診断給付金-疾病  半角数字  11  診断給付金-疾病の１保険事故あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element shindanKyufukinKibyo = null;
        if (!requestData.getData().getShindanKyufukinKibyo().isEmpty() || !requestData.getData().getShindanKyufukinKibyo().equals(EMPTY)) {
            shindanKyufukinKibyo = document.createElement("診断給付金-疾病");
            shindanKyufukinKibyo.setTextContent(requestData.getData().getShindanKyufukinKibyo());
        }
        // 診断給付金-要介護  診断給付金-要介護  半角数字  11  診断給付金-要介護の１保険事故あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element shindanKyufukinYokaigo = null;
        if (!requestData.getData().getShindanKyufukinYokaigo().isEmpty() || !requestData.getData().getShindanKyufukinYokaigo().equals(EMPTY)) {
            shindanKyufukinYokaigo = document.createElement("診断給付金-要介護");
            shindanKyufukinYokaigo.setTextContent(requestData.getData().getShindanKyufukinYokaigo());
        }
        // 入院給付金-特定疾病  入院給付金-特定疾病  半角数字  11  入院給付金-特定疾病の入院１日あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element nyuinkyufukinTokuteiKibyo = null;
        if (!requestData.getData().getNyuinkyufukinTokuteiKibyo().isEmpty() || !requestData.getData().getNyuinkyufukinTokuteiKibyo().equals(EMPTY)) {
            nyuinkyufukinTokuteiKibyo = document.createElement("入院給付金-特定疾病");
            nyuinkyufukinTokuteiKibyo.setTextContent(requestData.getData().getNyuinkyufukinTokuteiKibyo());
        }
        // 入院給付金-通常  入院給付金-通常  半角数字  11  入院給付金-通常の入院１日あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element nyuinkyufukinTujyo = null;
        if (!requestData.getData().getNyuinkyufukinTujyo().isEmpty() || !requestData.getData().getNyuinkyufukinTujyo().equals(EMPTY)) {
            nyuinkyufukinTujyo = document.createElement("入院給付金-通常");
            nyuinkyufukinTujyo.setTextContent(requestData.getData().getNyuinkyufukinTujyo());
        }
        // 手術給付金-特定疾病  手術給付金-特定疾病  半角数字  11  手術給付金-通常の１保険事故あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element syujyutukyufukinTokuteiKibyo = null;
        if (!requestData.getData().getSyujyutukyufukinTokuteiKibyo().isEmpty() || !requestData.getData().getSyujyutukyufukinTokuteiKibyo().equals(EMPTY)) {
            syujyutukyufukinTokuteiKibyo = document.createElement("手術給付金-特定疾病");
            syujyutukyufukinTokuteiKibyo.setTextContent(requestData.getData().getSyujyutukyufukinTokuteiKibyo());
        }
        // 手術給付金-通常  手術給付金-通常  半角数字  11  手術給付金-通常の１保険事故あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element syujyutukyufukinTujyo = null;
        if (!requestData.getData().getSyujyutukyufukinTujyo().isEmpty() || !requestData.getData().getSyujyutukyufukinTujyo().equals(EMPTY)) {
            syujyutukyufukinTujyo = document.createElement("手術給付金-通常");
            syujyutukyufukinTujyo.setTextContent(requestData.getData().getSyujyutukyufukinTujyo());
        }
        // 生存給付金  生存給付金  半角数字  11  生存給付金の１か月あたりの金額を設定
        // ※該当の給付金が発生する商品については新規データ還元時設定必須
        Element seizonkyufukin = null;
        if (!requestData.getData().getSeizonkyufukin().isEmpty() || !requestData.getData().getSeizonkyufukin().equals(EMPTY)) {
            seizonkyufukin = document.createElement("生存給付金");
            seizonkyufukin.setTextContent(requestData.getData().getSeizonkyufukin());
        }

        list.add(shindanKyufukinKibyo);
        list.add(shindanKyufukinYokaigo);
        list.add(nyuinkyufukinTokuteiKibyo);
        list.add(nyuinkyufukinTujyo);
        list.add(syujyutukyufukinTokuteiKibyo);
        list.add(syujyutukyufukinTujyo);
        list.add(seizonkyufukin);
        kyufukin = getUpperElement("給付金", document, list);
        list.clear();

        // 据置期間  据置期間  半角数字  3  据置期間を設定（単位：年）
        Element sueokiKikan = null;
        if (!requestData.getData().getSueokiKikan().isEmpty() || !requestData.getData().getSueokiKikan().equals(EMPTY)) {
            sueokiKikan = document.createElement("据置期間");
            sueokiKikan.setTextContent(requestData.getData().getSueokiKikan());
        }
        // 年間年金支払回数  年間年金支払回数  半角数字  3  年間の年金支払回数を設定
        // ※年金支払開始前、年金商品以外の場合は未使用（ZERO）
        // ※年金型商品については新規データ還元時設定必須
        Element nenkannenkinShiharaiKaisu = null;
        if (!requestData.getData().getNenkannenkinShiharaiKaisu().isEmpty() || !requestData.getData().getNenkannenkinShiharaiKaisu().equals(EMPTY)) {
            nenkannenkinShiharaiKaisu = document.createElement("年間年金支払回数");
            nenkannenkinShiharaiKaisu.setTextContent(requestData.getData().getNenkannenkinShiharaiKaisu());
        }

        list.add(haraikomiHoho);
        list.add(gokeiHokenryo);
        list.add(zenkiZennoHokenryo);
        list.add(syokaiHaraikomiHokenryo);
        list.add(syukeiyakuSyohinMei);
        list.add(syukeiyakuSyohinNo);
        list.add(syukeiyakuKikan);
        list.add(syukeiyakuKikanKubun);
        list.add(syukeiyakuHokenryoHaraikomiKikan);
        list.add(syukeiyakuHokenryoHaraikomiKikanKubun);
        list.add(syukeiyakuHokenkin);
        list.add(kyufukin);
        list.add(sueokiKikan);
        list.add(nenkannenkinShiharaiKaisu);
        keiyakuNaiyo = getUpperElement("契約内容", document, list);
        list.clear();

        if (keiyakuNaiyo != null) {
            data.appendChild(keiyakuNaiyo);
        }

        //headerを加工
        //=document.createElement("");
        Element header = document.createElement("Header");

        Element authKey = document.createElement("認証キー");
        authKey.setTextContent(requestData.getHeader().getAuthKey());
        header.appendChild(authKey);

        Element fileId = document.createElement("ファイルID");
        fileId.setTextContent(requestData.getHeader().getFileId());
        header.appendChild(fileId);

        Element transmitFromCd = document.createElement("送信元識別子");
        transmitFromCd.setTextContent(requestData.getHeader().getTransmitFromCd());
        header.appendChild(transmitFromCd);

        Element time = document.createElement("通信時刻");
        time.setTextContent(requestData.getHeader().getTime());
        header.appendChild(time);

        //headerとdataをセット
        Element apiRequestData = document.createElement("ApiRequestData");
        apiRequestData.appendChild(header);
        apiRequestData.appendChild(data);
        document.appendChild(apiRequestData);


        //XMLコンテンツを直接出力部
        //整形
        TransformerFactory tff = TransformerFactory.newInstance();
        ByteArrayOutputStream outputStream = null;
        try {
            Transformer tf = tff.newTransformer();
            DOMSource source = new DOMSource(document);
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            //直接出力
            outputStream = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            Result result = new StreamResult(writer);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println(outputStream.toString());
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        if (outputStream != null) {
            return outputStream.toString();
        } else {
            return null;
        }
    }

    private static Element getUpperElement(String upperName, Document document, List<Element> elements) {
        Element ele = null;
        int count = 0;
        for (Element element : elements) {
            if (element != null) {
                count++;
            }
        }
        if (count > 0) {
            ele = document.createElement(upperName);
            for (Element element : elements) {
                if (element != null) {
                    ele.appendChild(element);
                }
            }
        }
        return ele;
    }

}
