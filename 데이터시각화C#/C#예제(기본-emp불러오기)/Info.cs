using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace WindowsFormsApplication1
{
    class Info
    {
        //필드선언과 함께 설정자 접근자 선언
        public string Mac { get; set; }
        public string Time { get; set; }
        public string Name { get; set; }
        public string Battery { get; set; }
        public string Temp { get; set; }
        public string Humi { get; set; }
        public int Count { get; set; }
        //DataGridView 초기화 시 for문에서 Info 클래스 필드에 쉽게 접근하기 위해만든 함수
        public string getInfo(int num)
        {
            switch (num)
            {
                case 0:                
                    return Mac;
                case 1:
                    return Time;
                case 2:
                    return Name;
                case 3:
                    return Battery;
                case 4:
                    return Temp;
                case 5:
                    return Humi;
                default:
                    return "Error";
            }
        }

        public DateTime getTime()
        {
            DateTime d = DateTime.ParseExact(Time, "yyyy-MM-dd-HH-mm-ss", System.Globalization.CultureInfo.InvariantCulture);
            return d;
        }

        //Info 클래스 한줄묘사
        public override string ToString()
        {
            return "MAC: "+Mac + " 시간: " + Time + " 회사명: " + Name + " 배터리: " + Battery + " 온도: " + Temp + " 습도: " + Humi;
        }
    }
}
