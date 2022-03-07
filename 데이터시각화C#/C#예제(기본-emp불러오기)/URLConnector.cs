using System;
using System.IO;
using System.Net;
using System.Text;

namespace WindowsFormsApplication1
{
    public class URLConnector
    {
        private String result;  //질의결과가 담길 변수
        private String URL;     //URL 주소가 담길 변수

        //생성자
        public URLConnector(String url)
        {
            URL = url;     //URL을 받아 초기화
        }

        //Thread 사용시 쓰일 함수
        public void run()
        {
            result = request(URL);  //질의결과 할당
        }

        //result 접근자
        public String getResult()
        {
            return result;
        }

        //URL통신을 통해 DB에 접근하여 질의결과를 반환하는 함수
        private String request(String urlStr)
        {
            StringBuilder output = new StringBuilder();
            try
            {
                HttpWebRequest myReq = (HttpWebRequest)WebRequest.Create(urlStr);   //httpWed을 이용한 URL 연결 객체생성(클라이언트 소켓느낌)
                HttpWebResponse wRes = (HttpWebResponse)myReq.GetResponse();        //httpWed을 이용한 URL 응답 객체생성(서버 소켓느낌)

                // Response의 결과를 스트림을 생성합니다.
                Stream respGetStream = wRes.GetResponseStream();
                StreamReader readerGet = new StreamReader(respGetStream, Encoding.UTF8);    //스트림을 UTF8 문자열을 읽는 스트림으로 연결

                //스트림의 끝까지 반복
                while (!readerGet.EndOfStream)
                {
                    output.Append(readerGet.ReadLine());    //문자열을 더해감
                }
            }
            catch (Exception ex)
            {
            }
            return output.ToString();   //질의결과 반환
        }
    }
}