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

        //�T�[�o�֐ڑ�����R�l�N�V����
        HttpURLConnection connection = null;

        //�ȉ��̂悤�ɓo�^�E�X�V�p��URL��ݒ肷��B�iREGIST_UPDATE_URL��API�ڑ�URL��萔��`�j
        //https://smt7.hoken-gw.net/smtsso/fw/TAIYO-J1/tyoagent/PLTest.jsp?GW_UID=testtaiyo010
        URL url;
        try {
            url = new URL(REGIST_INSERT_URL);
            //�ڑ��pHttpURLConnection�I�u�W�F�N�g�쐬
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //(1)���M���̒ʐM�d�l��ݒ�
            // HTTP���\�b�h��POST���w�肵�܂��B
            connection.setRequestMethod("POST");
            connection.setInstanceFollowRedirects(false);
            //(2)���M����HTTP�w�b�_����ݒ�
            //���e��ށFContent-Type:application/xml
            //�����Z�b�g�Fcharset=UTF-8
            //���ݒ�C���[�W��
            // Content-Type:application/xml;charset=UTF-8
            connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // (3)HTTP�{�f�B�����쐬
        //�W���o�͂Ƀ��O��f��
        System.out.println("<------------------------���M�d���m�F START ------------------------>");
        //HTTP�{�f�B�[���ɐݒ肷������쐬���܂��B
        //�ڍׂ̓K�C�h���C���́u�ʎ� Web�t�@�C���]��API���M�����d�l���v���Q�ƁB
        String httpBody = getRegistReqBody();
        System.out.println("<-------------------------���M�d���m�F END ------------------------->");

        System.out.println(START);
        System.out.println(System.currentTimeMillis());
        System.out.println("xml�쐬���ԁi�~���b�j�F"+(System.currentTimeMillis()-START));


        //4.�f�[�^���M
        //�����R�[�h��UTF-8�ɐݒ�
        DataOutputStream os;
        int iResponseCode = 0;
        String result="";
        try {
            os=new DataOutputStream(Objects.requireNonNull(connection).getOutputStream());
            os.write(httpBody.getBytes("UTF-8"));
            //�^�C���A�E�g�ݒ�i�~���b�j
            connection.setReadTimeout(10000);
            // 5.���ʃR�[�h�̎擾
            //���ʃR�[�h���擾
            iResponseCode=connection.getResponseCode();
            if(iResponseCode==HttpURLConnection.HTTP_OK){
                StringBuilder resultBuilder=new StringBuilder();
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                //���X�|���X�̃f�[�^��Ǎ�
                String line;
                while ((line=br.readLine())!=null){
                    resultBuilder.append(line);
                }
                result=resultBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //�W���o�͂Ƀ��O��f��
        System.out.println("���X�|���X�̃f�[�^�͉��L�ł��B");
        System.out.println(iResponseCode);
        System.out.println(result);
    }

    @Override
    public void sendApiGW(String desDocNo) {

    }

    private static String getRegistReqBody(){
        //ApiRequestData�I�u�W�F�N�g��V�K�쐬
        ApiRequestData apiRequestData=new ApiRequestData();

        //���N�G�X�g�f�[�^�̍쐬
        Data data = new Data();

        //�{�f�B�[����ݒ�
        data.setInscoCd("�ی���ЃR�[�h");
        data.setGwId("����GW���pID");
        data.setDesDocNo("�݌v���ԍ�");

        data.setApplSt("�X�e�[�^�X");
        data.setApplNo("�\�����ԍ�");
        data.setSyokenNo("�،��ԍ�");
        data.setCaseManageNo("�Č��Ǘ��ԍ�");
        data.setDesDocNoDate("�݌v���쐬��");
        data.setApplDate("�\����");
        data.setBranchCode("�S���X��");
        data.setEmployeeNameKanji("��W�l��-����");
        data.setToriatsukaiNameKanji("�戵���F��-����");

        data.setKyksNameKatakana("���p�J�i");
        data.setKyksNameKanji("����");
        data.setKyksBirth("���N����");
        data.setKyksGender("����");
        data.setKyksAddressKanji("�Z��-����");
        data.setKyksCompanyNameKatakana("����-���p�J�i");
        data.setKyksCompanyNameKanji("����-����");

        data.setHihoNameKatakana("���p�J�i");
        data.setHihoNameKanji("����");
        data.setHihoBirth("���N����");
        data.setHihoAge("�N��");
        data.setHihoGender("����");
        data.setHihoAddressKanji("�Z��-����");

        data.setHihoCompanyNameKatakana("����-���p�J�i");
        data.setHihoCompanyNameKanji("����-����");

        data.setUketori1NameKatakana("���p�J�i1");
        data.setUketori1NameKanji("����1");
        data.setUketori1Birth("���N����1");
        data.setUketori1AddressKanji("�Z��-����1");

        data.setUketori2NameKatakana("���p�J�i2");
        data.setUketori2NameKanji("����2");
        data.setUketori2Birth("���N����2");
        data.setUketori2AddressKanji("�Z��-����2");

        data.setUketori3NameKatakana("���p�J�i3");
        data.setUketori3NameKanji("����3");
        data.setUketori3Birth("���N����3");
        data.setUketori3AddressKanji("�Z��-����3");

        data.setUketori4NameKatakana("���p�J�i4");
        data.setUketori4NameKanji("����4");
        data.setUketori4Birth("���N����4");
        data.setUketori4AddressKanji("�Z��-����4");

        data.setUketori5NameKatakana("���p�J�i5");
        data.setUketori5NameKanji("����5");
        data.setUketori5Birth("���N����5");
        data.setUketori5AddressKanji("�Z��-����5");

        data.setHaraikomiHoho("�ی����������@");

        data.setAmount("�ی���");
        data.setHaraikomiTukaCode("�ʉ݃R�[�h");
        data.setHaraikomiExRate("�בփ��[�g");
        data.setZenkiZennoHokenryo("�S���O�[�ی���");
        data.setSyokaiHaraikomiHokenryo("���񕥍����z");
        data.setSyukeiyakuSyohinMei("��_�񏤕i��-����");
        data.setSyukeiyakuSyohinNo("��_�񏤕i�ԍ�");
        data.setSyukeiyakuKikan("��_��ی�����");
        data.setSyukeiyakuKikanKubun("��_��ی����ԋ敪");
        data.setSyukeiyakuHokenryoHaraikomiKikan("��_��ی�����������");
        data.setSyukeiyakuHokenryoHaraikomiKikanKubun("��_��ی����������ԋ敪");

        data.setSyukeiyakuHokenNenkinKingaku("�ی����z-�N�����z");
        data.setTuyotukaCode("�ʉ݃R�[�h");
        data.setTuyotukaExRate("�בփ��[�g");

        data.setShindanKyufukinKibyo("�f�f���t��-���a");
        data.setShindanKyufukinYokaigo("�f�f���t��-�v���");
        data.setNyuinkyufukinTokuteiKibyo("���@���t��-���莾�a");
        data.setNyuinkyufukinTujyo("���@���t��-�ʏ�");
        data.setSyujyutukyufukinTokuteiKibyo("��p���t��-���莾�a");
        data.setSyujyutukyufukinTujyo("��p���t��-�ʏ�");
        data.setSeizonkyufukin("�������t��");
        data.setSueokiKikan("���u����");
        data.setNenkannenkinShiharaiKaisu("�N�ԔN���x����");
        apiRequestData.setData(data);

        //�w�b�_�̏���ݒ�
        Header header = new Header();
        //�w�b�_�[��apiRequestData�ɐݒ�
        //�F�؃L�[��ݒ�
        header.setAuthKey(header.getAuthKey());
        //�t�@�C��ID��ݒ�
//        header.setFileId(header.getFileId());
        //���M�����ʎq��ݒ�
//        header.setTransmitFromCd(header.getTransmitFromCd());
        //���M������ݒ�
        header.setTime(header.getTime());
        //�w�b�_�����N�G�X�g�f�[�^�ɒǉ�
        apiRequestData.setHeader(header);

        System.out.println(apiRequestData.toString());
        return createXMLString(apiRequestData);
    }

    private static DocumentBuilder getDocumentBuilder() {
        //DocumentBuilderFactory�I�u�W�F�N�g���쐬
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //DocumentBuilder�I�u�W�F�N�g���쐬
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

        //data�����H--�K�{�p�����[�^--
        Element data = document.createElement("Data");

        Element inscoCd = document.createElement("�ی���ЃR�[�h");
        inscoCd.setTextContent(requestData.getData().getInscoCd());
        data.appendChild(inscoCd);

        Element gwId = document.createElement("����GW���pID");
        gwId.setTextContent(requestData.getData().getGwId());
        data.appendChild(gwId);

        Element desDocNo = document.createElement("�݌v���ԍ�");
        desDocNo.setTextContent(requestData.getData().getDesDocNo());
        data.appendChild(desDocNo);

        //data�����H--���ʃp�����[�^--
        // �X�e�[�^�X  �X�e�[�^�X  ���p����  2  �ȉ��̒l��ݒ�""00"" �����F�A""01"" ���F�ς�
        // �i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        if (!requestData.getData().getApplSt().isEmpty() || !requestData.getData().getApplSt().equals(EMPTY)) {
            Element applSt = document.createElement("�X�e�[�^�X");
            applSt.setTextContent(requestData.getData().getApplSt());
            data.appendChild(applSt);
        }

        // �\�����ԍ�  �\�����ԍ�  ���p����  20  �\�����ԍ���ݒ�
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        if (!requestData.getData().getApplNo().isEmpty() || !requestData.getData().getApplNo().equals(EMPTY)) {
            Element applNo = document.createElement("�\�����ԍ�");
            applNo.setTextContent(requestData.getData().getApplNo());
            data.appendChild(applNo);
        }

        // �،��ԍ�  �،��ԍ�  ���p����  23  �،��ԍ���ݒ�
        if (!requestData.getData().getSyokenNo().isEmpty() || !requestData.getData().getSyokenNo().equals(EMPTY)) {
            Element syokenNo = document.createElement("�،��ԍ�");
            syokenNo.setTextContent(requestData.getData().getSyokenNo());
            data.appendChild(syokenNo);
        }

        // �Č��Ǘ��ԍ�  �Č��Ǘ��ԍ�  ���p����  15  �㗝�X�̈Č��Ǘ��ԍ���ݒ�
        if (!requestData.getData().getCaseManageNo().isEmpty() || !requestData.getData().getCaseManageNo().equals(EMPTY)) {
            Element caseManageNo = document.createElement("�Č��Ǘ��ԍ�");
            caseManageNo.setTextContent(requestData.getData().getCaseManageNo());
            data.appendChild(caseManageNo);
        }

        // �݌v���쐬��  �݌v���쐬��  ���p����  8  YYYYMMDD�FWeb��Ő݌v�����쐬���ꂽ�N������ݒ�(�N�E���E�� �e�X�OZERO)
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        if (!requestData.getData().getDesDocNoDate().isEmpty() || !requestData.getData().getDesDocNoDate().equals(EMPTY)) {
            Element desDocNoDate = document.createElement("�݌v���쐬��");
            desDocNoDate.setTextContent(requestData.getData().getDesDocNoDate());
            data.appendChild(desDocNoDate);
        }

        // �\����  �\����  ���p����  8  YYYYMMDD�FWeb��Ő\�������ꂽ�N������ݒ�(�N�E���E�� �e�X�OZERO)
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        if (!requestData.getData().getApplDate().isEmpty() || !requestData.getData().getApplDate().equals(EMPTY)) {
            Element applDate = document.createElement("�\����");
            applDate.setTextContent(requestData.getData().getApplDate());
            data.appendChild(applDate);
        }

        // �戵  (�O���[�v��)  -  -
        // �S���X��  �X��  ���p����  9  �㗝�X���ɂ����ď������镔���\���R�[�h��ݒ�
        // �S����  (�O���[�v��)  -  -
        // ��W�l��-����  ��W�l���i�����j  �S���p����  60  ��W�l���i�����j��ݒ�
        // �戵���F��-����  �戵���F�ҁi�����j  �S���p����  60  �戵���F�Җ��i�����j��ݒ�
        Element toriatsukai = null;
        Element branchCode = null;
        Element tantoSya = null;
        Element employeeNameKanji = null;
        Element toriatsukaiNameKanji = null;

        if (!requestData.getData().getBranchCode().isEmpty() || !requestData.getData().getBranchCode().equals(EMPTY)) {
            branchCode = document.createElement("�S���X��");
            branchCode.setTextContent(requestData.getData().getBranchCode());
        }
        if (!requestData.getData().getEmployeeNameKanji().isEmpty() || !requestData.getData().getEmployeeNameKanji().equals(EMPTY)) {
            employeeNameKanji = document.createElement("��W�l��-����");
            employeeNameKanji.setTextContent(requestData.getData().getEmployeeNameKanji());
        }
        if (!requestData.getData().getToriatsukaiNameKanji().isEmpty() || !requestData.getData().getToriatsukaiNameKanji().equals(EMPTY)) {
            toriatsukaiNameKanji = document.createElement("�戵���F��-����");
            toriatsukaiNameKanji.setTextContent(requestData.getData().getToriatsukaiNameKanji());
        }
        if (employeeNameKanji != null || toriatsukaiNameKanji != null) {
            tantoSya = document.createElement("�S����");
            if (employeeNameKanji != null) {
                tantoSya.appendChild(employeeNameKanji);
            }
            if (toriatsukaiNameKanji != null) {
                tantoSya.appendChild(toriatsukaiNameKanji);
            }
        }
        if (branchCode != null || tantoSya != null) {
            toriatsukai = document.createElement("�戵");
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

        // �_���  (�O���[�v��)  -  -
        // ����  (�O���[�v��)  -  -
        // ���p�J�i  �_��Җ��i���p�J�i�j  ���p����  120  �_��Җ��i���p�J�i�j��ݒ�
        // ����  �_��Җ��i�����j  �S���p����  60  �_��Җ��i�����j��ݒ�
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
            kyksNameKatakana = document.createElement("���p�J�i");
            kyksNameKatakana.setTextContent(requestData.getData().getKyksNameKatakana());
        }
        if (!requestData.getData().getKyksNameKanji().isEmpty() || !requestData.getData().getKyksNameKanji().equals(EMPTY)) {
            kyksNameKanji = document.createElement("����");
            kyksNameKanji.setTextContent(requestData.getData().getKyksNameKanji());
        }

        list.add(kyksNameKatakana);
        list.add(kyksNameKanji);
        kyksName = getUpperElement("����", document, list);
        list.clear();

        // ���N����  �_��Ґ��N����  ���p����  8  YYYYMMDD�F�_��҂̐��N������ݒ�(�N�E���E�� �e�X�OZERO)
        if (!requestData.getData().getKyksBirth().isEmpty() || !requestData.getData().getKyksBirth().equals(EMPTY)) {
            kyksBirth = document.createElement("���N����");
            kyksBirth.setTextContent(requestData.getData().getKyksBirth());
        }
        // ����  �_��Ґ���  ���p����  1  �R�[�h�l��1700�o�C�g�i�_��Ґ��ʁj�����Ƃ��܂�
        if (!requestData.getData().getKyksGender().isEmpty() || !requestData.getData().getKyksGender().isEmpty()) {
            kyksGender = document.createElement("����");
            kyksGender.setTextContent(requestData.getData().getKyksGender());
        }
        // �Z��-����  �_��ҏZ���i�����j  �S���p����  120  �_��ҏZ���i�����j��ݒ�
        if (!requestData.getData().getKyksAddressKanji().isEmpty() || !requestData.getData().getKyksAddressKanji().equals(EMPTY)) {
            kyksAddressKanji = document.createElement("�Z��-����");
            kyksAddressKanji.setTextContent(requestData.getData().getKyksAddressKanji());
        }
        // �Ζ��於  (�O���[�v��)  -  -
        // ����-���p�J�i  �_��ҋΖ���i���p�J�i�j  ���p����  50  �_��҂̋Ζ���y�ыΖ��揊���i���p�J�i�j��ݒ�
        // ����-����  �_��ҋΖ���i�����j  �S���p����  60  �_��҂̋Ζ���y�ыΖ��揊���i�����j��ݒ�
        if (!requestData.getData().getKyksCompanyNameKatakana().isEmpty() || !requestData.getData().getKyksCompanyNameKatakana().equals(EMPTY)) {
            kyksCompanyNameKatakana = document.createElement("����-���p�J�i");
            kyksCompanyNameKatakana.setTextContent(requestData.getData().getKyksCompanyNameKatakana());
        }
        if (!requestData.getData().getKyksCompanyNameKanji().isEmpty() || !requestData.getData().getKyksCompanyNameKanji().equals(EMPTY)) {
            kyksCompanyNameKanji = document.createElement("����-����");
            kyksCompanyNameKanji.setTextContent(requestData.getData().getKyksCompanyNameKanji());
        }
        list.add(kyksCompanyNameKatakana);
        list.add(kyksCompanyNameKanji);
        kyksKinmusaki = getUpperElement("�Ζ��於", document, list);
        list.clear();

        list.add(kyksName);
        list.add(kyksBirth);
        list.add(kyksGender);
        list.add(kyksAddressKanji);
        list.add(kyksKinmusaki);
        kyks = getUpperElement("�_���", document, list);
        list.clear();
        if (kyks != null) {
            data.appendChild(kyks);
        }


        // ��ی���  (�O���[�v��)  -  -  �_�퓯��̏ꍇ�A�_��҂̓����ڂƓ���l��ݒ�
        // ����  (�O���[�v��)  -  -
        // ���p�J�i  ��ی��Җ��i���p�J�i�j  ���p����  120  ��ی��Җ��i���p�J�i�j��ݒ�
        // ����  ��ی��Җ��i�����j  �S���p����  60  ��ی��Җ��i�����j��ݒ�
        // ���N����  ��ی��Ґ��N����  ���p����  8  YYYYMMDD�F��ی��҂̐��N������ݒ�(�N�E���E�� �e�X�OZERO)
        // �N��  ��ی��ҔN��  ���p����  3  ��ی��҂̔N���ݒ�i�ی����Z�o��N��j
        // ����  ��ی��Ґ���  ���p����  1  �R�[�h�l��1700�o�C�g�i��ی��Ґ��ʁj�����Ƃ��܂�
        // �Z��-����  ��ی��ҏZ���i�����j  �S���p����  120  ��ی��ҏZ���i�����j��ݒ�
        // �Ζ��於  (�O���[�v��)  -  -
        // ����-���p�J�i  ��ی��ҋΖ���i���p�J�i�j  ���p����  50  ��ی��҂̋Ζ���y�ыΖ��揊���i���p�J�i�j��ݒ�
        // ����-����  ��ی��ҋΖ���i�����j  �S���p����  60  ��ی��҂̋Ζ���y�ыΖ��揊���i�����j��ݒ�
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
            hihoNameKatakana = document.createElement("���p�J�i");
            hihoNameKatakana.setTextContent(requestData.getData().getHihoNameKatakana());
        }
        if (!requestData.getData().getHihoNameKanji().isEmpty() || !requestData.getData().getHihoNameKanji().equals(EMPTY)) {
            hihoNameKanji = document.createElement("����");
            hihoNameKanji.setTextContent(requestData.getData().getHihoNameKanji());
        }
        if (hihoNameKatakana != null || hihoNameKanji != null) {
            hihoName = document.createElement("����");
            if (hihoNameKatakana != null) {
                hihoName.appendChild(hihoNameKatakana);
            }
            if (hihoNameKanji != null) {
                hihoName.appendChild(hihoNameKanji);
            }
        }

        if (!requestData.getData().getHihoAge().isEmpty() || !requestData.getData().getHihoAge().equals(EMPTY)) {
            hihoAge = document.createElement("�N��");
            hihoAge.setTextContent(requestData.getData().getHihoAge());
        }
        if (!requestData.getData().getHihoBirth().isEmpty() || !requestData.getData().getHihoBirth().equals(EMPTY)) {
            hihoBirth = document.createElement("���N����");
            hihoBirth.setTextContent(requestData.getData().getHihoBirth());
        }
        if (!requestData.getData().getHihoGender().isEmpty() || !requestData.getData().getHihoGender().isEmpty()) {
            hihoGender = document.createElement("����");
            hihoGender.setTextContent(requestData.getData().getHihoGender());
        }

        if (!requestData.getData().getHihoAddressKanji().isEmpty() || !requestData.getData().getHihoAddressKanji().equals(EMPTY)) {
            hihoAddressKanji = document.createElement("�Z��-����");
            hihoAddressKanji.setTextContent(requestData.getData().getHihoAddressKanji());
        }

        if (!requestData.getData().getHihoCompanyNameKatakana().isEmpty() || !requestData.getData().getHihoCompanyNameKatakana().equals(EMPTY)) {
            hihoCompanyNameKatakana = document.createElement("����-���p�J�i");
            hihoCompanyNameKatakana.setTextContent(requestData.getData().getHihoCompanyNameKatakana());
        }
        if (!requestData.getData().getHihoCompanyNameKanji().isEmpty() || !requestData.getData().getHihoCompanyNameKanji().equals(EMPTY)) {
            hihoCompanyNameKanji = document.createElement("����-����");
            hihoCompanyNameKanji.setTextContent(requestData.getData().getHihoCompanyNameKanji());
        }
        list.add(hihoCompanyNameKatakana);
        list.add(hihoCompanyNameKanji);
        hihoKinmusaki = getUpperElement("�Ζ��於", document, list);
        list.clear();

        list.add(hihoName);
        list.add(hihoBirth);
        list.add(hihoAge);
        list.add(hihoGender);
        list.add(hihoAddressKanji);
        list.add(hihoKinmusaki);
        hiho = getUpperElement("��ی���", document, list);
        list.clear();
        if (hiho != null) {
            data.appendChild(hiho);
        }

        // ���l  (�O���[�v��)  -  -
        // ���l1  (�O���[�v��)  -  -
        // ����  (�O���[�v��)  -  -
        // ���p�J�i  ���l���P�i���p�J�i�j  ���p����  120  ���l�P�̎����i���p�J�i�j��ݒ�
        // ����  ���l���P�i�����j  �S���p����  60  ���l�P�̎����i�����j��ݒ�
        // ���N����  ���l���N�����P  ���p����  8  YYYYMMDD�F���l�P�̐��N������ݒ�(�N�E���E�� �e�X�OZERO)
        // �Z��-����  ���l�Z���P�i�����j  �S���p����  120  ���l�P�̏Z���i�����j��ݒ�
        Element uketori;
        Element uketori1;
        Element uketori1Name;
        Element uketori1NameKatakana = null;
        Element uketori1NameKanji = null;
        Element uketori1Birth = null;
        Element uketori1AddressKanji = null;

        if (!requestData.getData().getUketori1NameKatakana().isEmpty() || !requestData.getData().getUketori1NameKatakana().equals(EMPTY)) {
            uketori1NameKatakana = document.createElement("���p�J�i");
            uketori1NameKatakana.setTextContent(requestData.getData().getUketori1NameKatakana());
        }

        if (!requestData.getData().getUketori1NameKanji().isEmpty() || !requestData.getData().getUketori1NameKanji().equals(EMPTY)) {
            uketori1NameKanji = document.createElement("����");
            uketori1NameKanji.setTextContent(requestData.getData().getUketori1NameKanji());
        }

        list.add(uketori1NameKatakana);
        list.add(uketori1NameKanji);
        uketori1Name = getUpperElement("����", document, list);
        list.clear();

        if (!requestData.getData().getUketori1Birth().isEmpty() || !requestData.getData().getUketori1Birth().equals(EMPTY)) {
            uketori1Birth = document.createElement("���N����");
            uketori1Birth.setTextContent(requestData.getData().getUketori1Birth());
        }
        if (!requestData.getData().getUketori1AddressKanji().isEmpty() || !requestData.getData().getUketori1AddressKanji().equals(EMPTY)) {
            uketori1AddressKanji = document.createElement("�Z��-����");
            uketori1AddressKanji.setTextContent(requestData.getData().getUketori1AddressKanji());
        }

        list.add(uketori1Name);
        list.add(uketori1Birth);
        list.add(uketori1AddressKanji);
        uketori1 = getUpperElement("���l1", document, list);
        list.clear();

        Element uketori2;
        Element uketori2Name;
        Element uketori2NameKatakana = null;
        Element uketori2NameKanji = null;
        Element uketori2Birth = null;
        Element uketori2AddressKanji = null;

        if (!requestData.getData().getUketori2NameKatakana().isEmpty() || !requestData.getData().getUketori2NameKatakana().equals(EMPTY)) {
            uketori2NameKatakana = document.createElement("���p�J�i");
            uketori2NameKatakana.setTextContent(requestData.getData().getUketori2NameKatakana());
        }

        if (!requestData.getData().getUketori2NameKanji().isEmpty() || !requestData.getData().getUketori2NameKanji().equals(EMPTY)) {
            uketori2NameKanji = document.createElement("����");
            uketori2NameKanji.setTextContent(requestData.getData().getUketori2NameKanji());
        }

        list.add(uketori2NameKatakana);
        list.add(uketori2NameKanji);
        uketori2Name = getUpperElement("����", document, list);
        list.clear();

        if (!requestData.getData().getUketori2Birth().isEmpty() || !requestData.getData().getUketori2Birth().equals(EMPTY)) {
            uketori2Birth = document.createElement("���N����");
            uketori2Birth.setTextContent(requestData.getData().getUketori2Birth());
        }
        if (!requestData.getData().getUketori2AddressKanji().isEmpty() || !requestData.getData().getUketori2AddressKanji().equals(EMPTY)) {
            uketori2AddressKanji = document.createElement("�Z��-����");
            uketori2AddressKanji.setTextContent(requestData.getData().getUketori2AddressKanji());
        }

        list.add(uketori2Name);
        list.add(uketori2Birth);
        list.add(uketori2AddressKanji);
        uketori2 = getUpperElement("���l2", document, list);
        list.clear();

        Element uketori3;
        Element uketori3Name;
        Element uketori3NameKatakana = null;
        Element uketori3NameKanji = null;
        Element uketori3Birth = null;
        Element uketori3AddressKanji = null;

        if (!requestData.getData().getUketori3NameKatakana().isEmpty() || !requestData.getData().getUketori3NameKatakana().equals(EMPTY)) {
            uketori3NameKatakana = document.createElement("���p�J�i");
            uketori3NameKatakana.setTextContent(requestData.getData().getUketori3NameKatakana());
        }

        if (!requestData.getData().getUketori3NameKanji().isEmpty() || !requestData.getData().getUketori3NameKanji().equals(EMPTY)) {
            uketori3NameKanji = document.createElement("����");
            uketori3NameKanji.setTextContent(requestData.getData().getUketori3NameKanji());
        }

        list.add(uketori3NameKatakana);
        list.add(uketori3NameKanji);
        uketori3Name = getUpperElement("����", document, list);
        list.clear();

        if (!requestData.getData().getUketori3Birth().isEmpty() || !requestData.getData().getUketori3Birth().equals(EMPTY)) {
            uketori3Birth = document.createElement("���N����");
            uketori3Birth.setTextContent(requestData.getData().getUketori3Birth());
        }
        if (!requestData.getData().getUketori3AddressKanji().isEmpty() || !requestData.getData().getUketori3AddressKanji().equals(EMPTY)) {
            uketori3AddressKanji = document.createElement("�Z��-����");
            uketori3AddressKanji.setTextContent(requestData.getData().getUketori3AddressKanji());
        }

        list.add(uketori3Name);
        list.add(uketori3Birth);
        list.add(uketori3AddressKanji);
        uketori3 = getUpperElement("���l3", document, list);
        list.clear();

        Element uketori4;
        Element uketori4Name;
        Element uketori4NameKatakana = null;
        Element uketori4NameKanji = null;
        Element uketori4Birth = null;
        Element uketori4AddressKanji = null;

        if (!requestData.getData().getUketori4NameKatakana().isEmpty() || !requestData.getData().getUketori4NameKatakana().equals(EMPTY)) {
            uketori4NameKatakana = document.createElement("���p�J�i");
            uketori4NameKatakana.setTextContent(requestData.getData().getUketori4NameKatakana());
        }

        if (!requestData.getData().getUketori4NameKanji().isEmpty() || !requestData.getData().getUketori4NameKanji().equals(EMPTY)) {
            uketori4NameKanji = document.createElement("����");
            uketori4NameKanji.setTextContent(requestData.getData().getUketori4NameKanji());
        }

        list.add(uketori4NameKatakana);
        list.add(uketori4NameKanji);
        uketori4Name = getUpperElement("����", document, list);
        list.clear();

        if (!requestData.getData().getUketori4Birth().isEmpty() || !requestData.getData().getUketori4Birth().equals(EMPTY)) {
            uketori4Birth = document.createElement("���N����");
            uketori4Birth.setTextContent(requestData.getData().getUketori4Birth());
        }
        if (!requestData.getData().getUketori4AddressKanji().isEmpty() || !requestData.getData().getUketori4AddressKanji().equals(EMPTY)) {
            uketori4AddressKanji = document.createElement("�Z��-����");
            uketori4AddressKanji.setTextContent(requestData.getData().getUketori4AddressKanji());
        }

        list.add(uketori4Name);
        list.add(uketori4Birth);
        list.add(uketori4AddressKanji);
        uketori4 = getUpperElement("���l4", document, list);
        list.clear();

        Element uketori5;
        Element uketori5Name;
        Element uketori5NameKatakana = null;
        Element uketori5NameKanji = null;
        Element uketori5Birth = null;
        Element uketori5AddressKanji = null;

        if (!requestData.getData().getUketori5NameKatakana().isEmpty() || !requestData.getData().getUketori5NameKatakana().equals(EMPTY)) {
            uketori5NameKatakana = document.createElement("���p�J�i");
            uketori5NameKatakana.setTextContent(requestData.getData().getUketori5NameKatakana());
        }

        if (!requestData.getData().getUketori5NameKanji().isEmpty() || !requestData.getData().getUketori5NameKanji().equals(EMPTY)) {
            uketori5NameKanji = document.createElement("����");
            uketori5NameKanji.setTextContent(requestData.getData().getUketori5NameKanji());
        }

        list.add(uketori5NameKatakana);
        list.add(uketori5NameKanji);
        uketori5Name = getUpperElement("����", document, list);
        list.clear();

        if (!requestData.getData().getUketori5Birth().isEmpty() || !requestData.getData().getUketori5Birth().equals(EMPTY)) {
            uketori5Birth = document.createElement("���N����");
            uketori5Birth.setTextContent(requestData.getData().getUketori5Birth());
        }
        if (!requestData.getData().getUketori5AddressKanji().isEmpty() || !requestData.getData().getUketori5AddressKanji().equals(EMPTY)) {
            uketori5AddressKanji = document.createElement("�Z��-����");
            uketori5AddressKanji.setTextContent(requestData.getData().getUketori5AddressKanji());
        }

        list.add(uketori5Name);
        list.add(uketori5Birth);
        list.add(uketori5AddressKanji);
        uketori5 = getUpperElement("���l5", document, list);
        list.clear();

        list.add(uketori1);
        list.add(uketori2);
        list.add(uketori3);
        list.add(uketori4);
        list.add(uketori5);
        uketori = getUpperElement("���l", document, list);
        list.clear();

        if (uketori != null) {
            data.appendChild(uketori);
        }

        // �_����e  (�O���[�v��)  -  -
        Element keiyakuNaiyo;
        // �ی����������@  �ی����������@  ���p����  1  �R�[�h�l��1700�o�C�g�i�������@�j�����Ƃ��܂�
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element haraikomiHoho = null;
        if (!requestData.getData().getHaraikomiHoho().isEmpty() || !requestData.getData().getHaraikomiHoho().equals(EMPTY)) {
            haraikomiHoho = document.createElement("�ی����������@");
            haraikomiHoho.setTextContent(requestData.getData().getHaraikomiHoho());
        }
        // ���v�ی���  (�O���[�v��)  -  -
        Element gokeiHokenryo;
        // �ی���  �������z  ���p����  11  �������z�̒l��ݒ�B
        //�ꎞ���̏ꍇ�͈ꎞ���ی�����ݒ�
        //�������̏ꍇ�͏���ی�����ݒ�
        //�O�݂̏ꍇ�͕⏕�ʉݒP�ʂɂĐݒ�
        //�i��j12�h��34�Z���g��123412�h���i0�Z���g�j��1200
        // ���ی����Ƃ͎�_��ی����{����ی����{�O�[�ی�����\���܂��B
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element amount = null;
        if (!requestData.getData().getAmount().isEmpty() || !requestData.getData().getAmount().equals(EMPTY)) {
            amount = document.createElement("�ی���");
            amount.setTextContent(requestData.getData().getAmount());
        }
        // �ʉ݃R�[�h  �ʉ݃R�[�h�i�������j  ���p����  3  �������̒ʉ݃R�[�h��ݒ�
        // �R�[�h�l�͐��۔ėp�Ŏ萔���f�[�^�i�ʉ݃R�[�h�j�����Ƃ��܂��B
        // �i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element haraikomiTukaCode = null;
        if (!requestData.getData().getHaraikomiTukaCode().isEmpty() || !requestData.getData().getHaraikomiTukaCode().equals(EMPTY)) {
            haraikomiTukaCode = document.createElement("�ʉ݃R�[�h");
            haraikomiTukaCode.setTextContent(requestData.getData().getHaraikomiTukaCode());
        }
        // �בփ��[�g  �בփ��[�g�i�ی����j  ���p����  10  �ی������̉~���Z���̈בփ��[�g��ݒ�
        // �x�����ʉ݂��~�̏ꍇ��ZERO��ݒ�
        //�s�������F�O��7���B�������F���3���B���l�ߌ�0�ݒ�B�t
        // �i��j103.50��103500 123.00��123000
        // ���ی������O�݂̏��i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element haraikomiExRate = null;
        if (!requestData.getData().getHaraikomiExRate().isEmpty() || !requestData.getData().getHaraikomiExRate().equals(EMPTY)) {
            haraikomiExRate = document.createElement("�בփ��[�g");
            haraikomiExRate.setTextContent(requestData.getData().getHaraikomiExRate());
        }
        list.add(amount);
        list.add(haraikomiTukaCode);
        list.add(haraikomiExRate);
        gokeiHokenryo = getUpperElement("���v�ی���", document, list);
        list.clear();
        // �S���O�[�ی���  �S���O�[�ی���  ���p����  11  �S���O�[�̏ꍇ�̑O�[�ی�����ݒ�
        // �O�݌��Ă̏ꍇ�͕⏕�ʉݒP�ʂɂĐݒ�
        //�i��j12�h��34�Z���g��123412�h���i0�Z���g�j��1200
        // ���ꕔ�O�[�̏ꍇ���ݒ�
        Element zenkiZennoHokenryo = null;
        if (!requestData.getData().getZenkiZennoHokenryo().isEmpty() || !requestData.getData().getZenkiZennoHokenryo().equals(EMPTY)) {
            zenkiZennoHokenryo = document.createElement("�S���O�[�ی���");
            zenkiZennoHokenryo.setTextContent(requestData.getData().getZenkiZennoHokenryo());
        }
        // ���񕥍����z  ���񕥍��ی���  ���p����  11  ���񕥍����z��ݒ�
        // �����񕪂܂Ƃ߂ĕی����𕥂����ޏꍇ�A���񕪂̍��v�l��ݒ�
        //�O�݌��Ă̏ꍇ�͕⏕�ʉݒP�ʂɂĐݒ�
        //�i��j12�h��34�Z���g��123412�h���i0�Z���g�j��1200
        // �i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element syokaiHaraikomiHokenryo = null;
        if (!requestData.getData().getSyokaiHaraikomiHokenryo().isEmpty() || !requestData.getData().getSyokaiHaraikomiHokenryo().equals(EMPTY)) {
            syokaiHaraikomiHokenryo = document.createElement("���񕥍����z");
            syokaiHaraikomiHokenryo.setTextContent(requestData.getData().getSyokaiHaraikomiHokenryo());
        }
        // ��_�񏤕i��-����  ��_�񏤕i���i�����j  �S���p����  120  �ی����i���i�����j�i�y�b�g�l�[���j��ݒ�
        // ���Ђ炪�ȁ^�J�^�J�i�^�p�����̐ݒ��
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element syukeiyakuSyohinMei = null;
        if (!requestData.getData().getSyukeiyakuSyohinMei().isEmpty() || !requestData.getData().getSyukeiyakuSyohinMei().equals(EMPTY)) {
            syukeiyakuSyohinMei = document.createElement("��_�񏤕i��-����");
            syukeiyakuSyohinMei.setTextContent(requestData.getData().getSyukeiyakuSyohinMei());
        }
        // ��_�񏤕i�ԍ�  ��_�񏤕i�ԍ�  ���p����  7  �e�ی���Ђ����i���ɕt�^����Ǝ��R�[�h��ݒ�
        // ���f�[�^�A�g���ځu��_�񏤕i�ԍ��v�Ɠ����l��ݒ�
        // �i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element syukeiyakuSyohinNo = null;
        if (!requestData.getData().getSyukeiyakuSyohinNo().isEmpty() || !requestData.getData().getSyukeiyakuSyohinNo().equals(EMPTY)) {
            syukeiyakuSyohinNo = document.createElement("��_�񏤕i�ԍ�");
            syukeiyakuSyohinNo.setTextContent(requestData.getData().getSyukeiyakuSyohinNo());
        }
        // ��_��ی�����  ��_��ی�����  ���p����  3  �N�����̏ꍇ�́A�ی����Ԃ�ݒ�i�P�ʁF�N�j
        // �Ζ����̏ꍇ�́A�������N���ݒ�
        // �I�g�̏ꍇ�́AZERO��ݒ�
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element syukeiyakuKikan = null;
        if (!requestData.getData().getSyukeiyakuKikan().isEmpty() || !requestData.getData().getSyukeiyakuKikan().equals(EMPTY)) {
            syukeiyakuKikan = document.createElement("��_��ی�����");
            syukeiyakuKikan.setTextContent(requestData.getData().getSyukeiyakuKikan());
        }
        // ��_��ی����ԋ敪  ��_��ی����ԋ敪  ���p����  1  �R�[�h�l��1700�o�C�g�i�ی����ԋ敪�j�����Ƃ��܂�
        //�i�V�K�f�[�^�Ҍ����ݒ�K�{�j
        Element syukeiyakuKikanKubun = null;
        if (!requestData.getData().getSyukeiyakuKikanKubun().isEmpty() || !requestData.getData().getSyukeiyakuKikanKubun().equals(EMPTY)) {
            syukeiyakuKikanKubun = document.createElement("��_��ی����ԋ敪");
            syukeiyakuKikanKubun.setTextContent(requestData.getData().getSyukeiyakuKikanKubun());
        }
        // ��_��ی�����������  ��_��ی�����������  ���p����  3  �N�����̏ꍇ�́A�ی����Ԃ�ݒ�i�P�ʁF�N�j
        // �Ε����̏ꍇ�́A�����I�����N���ݒ�
        // �ꎞ�����A�I�g�����̏ꍇ�́AZERO��ݒ�
        Element syukeiyakuHokenryoHaraikomiKikan = null;
        if (!requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan().isEmpty() || !requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan().equals(EMPTY)) {
            syukeiyakuHokenryoHaraikomiKikan = document.createElement("��_��ی�����������");
            syukeiyakuHokenryoHaraikomiKikan.setTextContent(requestData.getData().getSyukeiyakuHokenryoHaraikomiKikan());
        }
        // ��_��ی����������ԋ敪  ��_��ی����������ԋ敪  ���p����  1  �R�[�h�l��1700�o�C�g�i�������ԋ敪�j�����Ƃ��܂�
        // ���ꎞ�����̏ꍇ�͏����l��ݒ�
        Element syukeiyakuHokenryoHaraikomiKikanKubun = null;
        if (!requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun().isEmpty() || !requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun().equals(EMPTY)) {
            syukeiyakuHokenryoHaraikomiKikanKubun = document.createElement("��_��ی����������ԋ敪");
            syukeiyakuHokenryoHaraikomiKikanKubun.setTextContent(requestData.getData().getSyukeiyakuHokenryoHaraikomiKikanKubun());
        }
        // ��_��ی���  (�O���[�v��)  -  -
        Element syukeiyakuHokenkin;
        // �ی����z-�N�����z  ��_��ی����z�^�N�����z  ���p����  11  ��_��ی����܂��͔N�����z��ݒ�
        // �O�݌��Ă̏ꍇ�́A�⏕�ʉݒP�ʂɂĐݒ�
        // �����S�ی����A�����ی����A�N���N�z���̏��i�̓����ɉ��������z��ݒ�
        //�i��j12�h��34�Z���g��123412�h���i0�Z���g�j��1200
        // �i�����ݒ�K�{�j
        Element syukeiyakuHokenNenkinKingaku = null;
        if (!requestData.getData().getSyukeiyakuHokenNenkinKingaku().isEmpty() || !requestData.getData().getSyukeiyakuHokenNenkinKingaku().equals(EMPTY)) {
            syukeiyakuHokenNenkinKingaku = document.createElement("�ی����z-�N�����z");
            syukeiyakuHokenNenkinKingaku.setTextContent(requestData.getData().getSyukeiyakuHokenNenkinKingaku());
        }
        // �ʉ݃R�[�h  �ʉ݃R�[�h�i�^�p�ʉ݁j  ���p����  3  �^�p�ʉ݂̒ʉ݃R�[�h��ݒ�
        // �R�[�h�l�͐��۔ėp�Ŏ萔���f�[�^�i�ʉ݃R�[�h�j�����Ƃ��܂��B
        // �i�����ݒ�K�{�j
        Element tuyotukaCode = null;
        if (!requestData.getData().getTuyotukaCode().isEmpty() || !requestData.getData().getTuyotukaCode().equals(EMPTY)) {
            tuyotukaCode = document.createElement("�ʉ݃R�[�h");
            tuyotukaCode.setTextContent(requestData.getData().getTuyotukaCode());
        }
        // �בփ��[�g  �בփ��[�g�i�^�p�ʉ݁j  ���p����  10  �^�p�ʉ݂̉~���Z���̈בփ��[�g��ݒ�
        // �^�p�ʉ݂��~�̏ꍇ��ZERO��ݒ�
        //�s�������F�O��7���B�������F���3���B���l�ߌ�0�ݒ�B�t
        // �i��j103.50��103500 123.00��123000
        // ���^�p�ʉ݂��O�݂̏��i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element tuyotukaExRate = null;
        if (!requestData.getData().getTuyotukaExRate().isEmpty() || !requestData.getData().getTuyotukaExRate().equals(EMPTY)) {
            tuyotukaExRate = document.createElement("�בփ��[�g");
            tuyotukaExRate.setTextContent(requestData.getData().getTuyotukaExRate());
        }

        list.add(syukeiyakuHokenNenkinKingaku);
        list.add(tuyotukaCode);
        list.add(tuyotukaExRate);
        syukeiyakuHokenkin = getUpperElement("��_��ی���", document, list);
        list.clear();

        // ���t��  (�O���[�v��)  -  -
        Element kyufukin;
        // �f�f���t��-���a  �f�f���t��-���a  ���p����  11  �f�f���t��-���a�̂P�ی����̂�����̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element shindanKyufukinKibyo = null;
        if (!requestData.getData().getShindanKyufukinKibyo().isEmpty() || !requestData.getData().getShindanKyufukinKibyo().equals(EMPTY)) {
            shindanKyufukinKibyo = document.createElement("�f�f���t��-���a");
            shindanKyufukinKibyo.setTextContent(requestData.getData().getShindanKyufukinKibyo());
        }
        // �f�f���t��-�v���  �f�f���t��-�v���  ���p����  11  �f�f���t��-�v���̂P�ی����̂�����̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element shindanKyufukinYokaigo = null;
        if (!requestData.getData().getShindanKyufukinYokaigo().isEmpty() || !requestData.getData().getShindanKyufukinYokaigo().equals(EMPTY)) {
            shindanKyufukinYokaigo = document.createElement("�f�f���t��-�v���");
            shindanKyufukinYokaigo.setTextContent(requestData.getData().getShindanKyufukinYokaigo());
        }
        // ���@���t��-���莾�a  ���@���t��-���莾�a  ���p����  11  ���@���t��-���莾�a�̓��@�P��������̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element nyuinkyufukinTokuteiKibyo = null;
        if (!requestData.getData().getNyuinkyufukinTokuteiKibyo().isEmpty() || !requestData.getData().getNyuinkyufukinTokuteiKibyo().equals(EMPTY)) {
            nyuinkyufukinTokuteiKibyo = document.createElement("���@���t��-���莾�a");
            nyuinkyufukinTokuteiKibyo.setTextContent(requestData.getData().getNyuinkyufukinTokuteiKibyo());
        }
        // ���@���t��-�ʏ�  ���@���t��-�ʏ�  ���p����  11  ���@���t��-�ʏ�̓��@�P��������̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element nyuinkyufukinTujyo = null;
        if (!requestData.getData().getNyuinkyufukinTujyo().isEmpty() || !requestData.getData().getNyuinkyufukinTujyo().equals(EMPTY)) {
            nyuinkyufukinTujyo = document.createElement("���@���t��-�ʏ�");
            nyuinkyufukinTujyo.setTextContent(requestData.getData().getNyuinkyufukinTujyo());
        }
        // ��p���t��-���莾�a  ��p���t��-���莾�a  ���p����  11  ��p���t��-�ʏ�̂P�ی����̂�����̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element syujyutukyufukinTokuteiKibyo = null;
        if (!requestData.getData().getSyujyutukyufukinTokuteiKibyo().isEmpty() || !requestData.getData().getSyujyutukyufukinTokuteiKibyo().equals(EMPTY)) {
            syujyutukyufukinTokuteiKibyo = document.createElement("��p���t��-���莾�a");
            syujyutukyufukinTokuteiKibyo.setTextContent(requestData.getData().getSyujyutukyufukinTokuteiKibyo());
        }
        // ��p���t��-�ʏ�  ��p���t��-�ʏ�  ���p����  11  ��p���t��-�ʏ�̂P�ی����̂�����̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element syujyutukyufukinTujyo = null;
        if (!requestData.getData().getSyujyutukyufukinTujyo().isEmpty() || !requestData.getData().getSyujyutukyufukinTujyo().equals(EMPTY)) {
            syujyutukyufukinTujyo = document.createElement("��p���t��-�ʏ�");
            syujyutukyufukinTujyo.setTextContent(requestData.getData().getSyujyutukyufukinTujyo());
        }
        // �������t��  �������t��  ���p����  11  �������t���̂P����������̋��z��ݒ�
        // ���Y���̋��t�����������鏤�i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element seizonkyufukin = null;
        if (!requestData.getData().getSeizonkyufukin().isEmpty() || !requestData.getData().getSeizonkyufukin().equals(EMPTY)) {
            seizonkyufukin = document.createElement("�������t��");
            seizonkyufukin.setTextContent(requestData.getData().getSeizonkyufukin());
        }

        list.add(shindanKyufukinKibyo);
        list.add(shindanKyufukinYokaigo);
        list.add(nyuinkyufukinTokuteiKibyo);
        list.add(nyuinkyufukinTujyo);
        list.add(syujyutukyufukinTokuteiKibyo);
        list.add(syujyutukyufukinTujyo);
        list.add(seizonkyufukin);
        kyufukin = getUpperElement("���t��", document, list);
        list.clear();

        // ���u����  ���u����  ���p����  3  ���u���Ԃ�ݒ�i�P�ʁF�N�j
        Element sueokiKikan = null;
        if (!requestData.getData().getSueokiKikan().isEmpty() || !requestData.getData().getSueokiKikan().equals(EMPTY)) {
            sueokiKikan = document.createElement("���u����");
            sueokiKikan.setTextContent(requestData.getData().getSueokiKikan());
        }
        // �N�ԔN���x����  �N�ԔN���x����  ���p����  3  �N�Ԃ̔N���x���񐔂�ݒ�
        // ���N���x���J�n�O�A�N�����i�ȊO�̏ꍇ�͖��g�p�iZERO�j
        // ���N���^���i�ɂ��Ă͐V�K�f�[�^�Ҍ����ݒ�K�{
        Element nenkannenkinShiharaiKaisu = null;
        if (!requestData.getData().getNenkannenkinShiharaiKaisu().isEmpty() || !requestData.getData().getNenkannenkinShiharaiKaisu().equals(EMPTY)) {
            nenkannenkinShiharaiKaisu = document.createElement("�N�ԔN���x����");
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
        keiyakuNaiyo = getUpperElement("�_����e", document, list);
        list.clear();

        if (keiyakuNaiyo != null) {
            data.appendChild(keiyakuNaiyo);
        }

        //header�����H
        //=document.createElement("");
        Element header = document.createElement("Header");

        Element authKey = document.createElement("�F�؃L�[");
        authKey.setTextContent(requestData.getHeader().getAuthKey());
        header.appendChild(authKey);

        Element fileId = document.createElement("�t�@�C��ID");
        fileId.setTextContent(requestData.getHeader().getFileId());
        header.appendChild(fileId);

        Element transmitFromCd = document.createElement("���M�����ʎq");
        transmitFromCd.setTextContent(requestData.getHeader().getTransmitFromCd());
        header.appendChild(transmitFromCd);

        Element time = document.createElement("�ʐM����");
        time.setTextContent(requestData.getHeader().getTime());
        header.appendChild(time);

        //header��data���Z�b�g
        Element apiRequestData = document.createElement("ApiRequestData");
        apiRequestData.appendChild(header);
        apiRequestData.appendChild(data);
        document.appendChild(apiRequestData);


        //XML�R���e���c�𒼐ڏo�͕�
        //���`
        TransformerFactory tff = TransformerFactory.newInstance();
        ByteArrayOutputStream outputStream = null;
        try {
            Transformer tf = tff.newTransformer();
            DOMSource source = new DOMSource(document);
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            //���ڏo��
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
