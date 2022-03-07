using System.Windows.Forms;
using System.Threading;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms.DataVisualization.Charting;
using System.Drawing;
using System;

namespace WindowsFormsApplication1
{

    public partial class Form1 : Form
    {
        private static bool DMac_Button_Check = false;      //DataGridView 부분 모든 MAC 체크 버튼 클릭확인 변수
        private static bool DCompany_Button_Check = false;  //DataGridView 부분 모든 Company 체크 버튼 클릭확인 변수
        private static bool GMac_Button_Check = false;      //Graph 부분 모든 MAC 체크 버튼 클릭확인 변수

        List<Info> Dinfo = new List<Info>(); //DataGridView 부분 인포클래스 리스트
        List<Info> Ginfo = new List<Info>(); //Graph 부분 인포클래스 리스트
        Color[] ccc = new Color[3];

        public Form1()
        {
            InitializeComponent();
            Init_Mac_CheckedListBox();  //MAC 체크 리스트 초기화
        }

        /*----------------------------------------------------초기화, 할당 관련 함수---------------------------------------------------*/
        /*  Dinfo 업데이트 및 DataGridView 업데이트  */
        public void update()
        {
            updateData();
            updateGrid();
        }
        /*  Dinfo 업데이트  */
        public void updateData()
        {
            //MAC은 필수이기 때문에 where을 바로 붙여줌
            string url= URLString.readinfo + " where ";

            string[] u = new string[6];

            //각 컬럼별 부분 URL 할당
            u[0] = generateURL("MAC",DmacCheckB.CheckedItems.Count,false);
            u[1] = generateURL("시간", 0, DSdatePicker.Enabled);
            u[2] = generateURL("회사명", DcompanyCheckB.CheckedItems.Count, DcompanyCheckB.Enabled);
            u[3] = generateURL("배터리", 0, DminBattery.Enabled);
            u[4] = generateURL("온도", 0, DminTemp.Enabled);
            u[5] = generateURL("습도", 0, DminHumi.Enabled);
            
            //MAC이 아닌 다른 컬럼이 있는지 검사하여 있으면 and를 붙여줌
            for(int i=0; i<u.Length; i++)
            {
                if (!u[i].Equals(""))
                {
                    for(int j = i+1; j<u.Length; j++)
                    {
                        if (!u[j].Equals(""))
                        {
                            u[i] += " and ";
                            break;
                        }
                    }
                    url += u[i];
                }
                
            }

            //MessageBox.Show(url);
            Dinfo = initInfo(url,6);    //Dinfo 업데이트
        }
        /*  DataGridView 업데이트   */
        public void updateGrid()

        {
            dataGridView1.Rows.Clear();
            if (Dinfo.Count > 0)
            {
                dataGridView1.ColumnCount = Dinfo[0].Count;
                dataGridView1.Columns[0].Name = "MAC";
                dataGridView1.Columns[1].Name = "시간";
                dataGridView1.Columns[2].Name = "회사명";
                dataGridView1.Columns[3].Name = "배터리";
                dataGridView1.Columns[4].Name = "온도";
                dataGridView1.Columns[5].Name = "습도";

                for (int i = 0; i < Dinfo.Count; i++)
                {
                    object[] obj = new object[Dinfo[0].Count]; // 필드수만큼 오브젝트 배열

                    for (int j = 0; j < Dinfo[0].Count; j++) // 필드 수만큼 반복
                    {
                        obj[j] = new object();
                        obj[j] = Dinfo[i].getInfo(j); // 오브젝트배열에 데이터 저장
                    }

                    dataGridView1.Rows.Add(obj); //데이터그리드뷰에 오브젝트 배열 추가
                }
                dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                dataGridView1.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.None;
            }
            else
            {
                MessageBox.Show("결과값이 없습니다!");
            }
        }  
        /* DB에 있는 MAC Address 를 받아와서 목록에 추가하는 함수 */
        private void Init_Mac_CheckedListBox()
        {
            string url = URLString.readmac;

            Ginfo = initInfo(url,1);
            foreach (Info f in Ginfo)
            {
                GmacCheckB.Items.Add(f.Mac);
                DmacCheckB.Items.Add(f.Mac);
            }
        }
        /*  질의결과를 가져와서 Info 리스트 초기화하는 함수 */
        private List<Info> initInfo(string url, int num)
        {
            /*  URL 통신시작  */
            URLConnector u = new URLConnector(url); //통신객체 생성
            Thread t = new Thread(new ThreadStart(u.run));  //통신객체안에 정의된 run함수를 쓰레드로 돌림
            t.Start();  //쓰레드 시작
            t.Join();   //쓰레드 종료 기다림
            /*  URL 통신종료  */

            //  문자열 JSON 객체화
            JObject jo = JObject.Parse(u.getResult());
            JArray ja = JArray.Parse(jo["result"].ToString());
            //JObject를 문자열 result가 가진 문자열들의 배열로 변환하여 JArray 객체생성
            //JObject의 형식 -> result : { "MAC": 11:11:11:11:11, "시간":2019-10-10-10-10-10, ...}

            List<Info> info = new List<Info>();  //각각의 레코드를 담을 Info 클래스형 List객체 생성

            //컬럼 개수가 6인 경우
            if (num == 6)
            {
                //JArray의 끝까지 반복
                foreach (JObject item in ja)
                {
                    //Info 객체 초기화
                    Info i = new Info();
                    i.Mac = item["MAC"].ToString();
                    i.Time = item["시간"].ToString();
                    i.Name = item["회사명"].ToString();
                    i.Battery = item["배터리"].ToString();
                    i.Temp = item["온도"].ToString();
                    i.Humi = item["습도"].ToString();
                    i.Count = num;
                    info.Add(i);
                }
            }
            //컬럼 개수가 1인 경우
            else if(num == 1)
            {
                //JArray의 끝까지 반복
                foreach (JObject item in ja)
                {
                    //Info 객체 초기화
                    Info i = new Info();
                    i.Mac = item["MAC"].ToString();
                    i.Count = num;
                    info.Add(i);
                }
            }
            //MessageBox.Show(info[0].ToString());
            return info;
        }   
        /*  케이스별 url 문자열을 만들어 반환해주는 함수  */
        private string generateURL(string c, int num, bool t)
        {
            string url = "";

            switch (c)
            {
                //MAC 관련 url 문자열 생성
                case "MAC":
                    if (num > 0)
                    {
                        url += "(";
                        for (int i = 0; i < DmacCheckB.CheckedItems.Count; i++)
                        {
                            url += "MAC = '" + DmacCheckB.CheckedItems[i] + "'";
                            //여러개의 MAC을 선택한 경우
                            if (i + 1 < DmacCheckB.CheckedItems.Count)
                            {
                                url += " or ";
                            }
                        }
                        url += ")";
                    }
                    break;
                //시간 관련 url 문자열 생성
                case "시간":
                    if (t)
                    {
                        url += "(";
                        string sdate = DSdatePicker.Text + "-" + DStimePickerH.Value.ToString("00") + "-" + DStimePickerM.Value.ToString("00") + "-" + DStimePickerS.Value.ToString("00");
                        string edate = DEdatePicker.Text + "-" + DEtimePickerH.Value.ToString("00") + "-" + DEtimePickerM.Value.ToString("00") + "-" + DEtimePickerS.Value.ToString("00");
                        url += "시간 >= '" + sdate + "' and 시간 < '" + edate + "')";
                    }
                    break;
                //회사명 관련 url 문자열 생성
                case "회사명":
                    if (num > 0 && t)
                    {
                        url += "(";
                        for (int i = 0; i < DcompanyCheckB.CheckedItems.Count; i++)
                        {
                            url += "회사명 = '" + DcompanyCheckB.CheckedItems[i] + "'";
                            //여러개의 회사명을 선택한 경우
                            if (i + 1 < DcompanyCheckB.CheckedItems.Count)
                            {
                                url += " or ";
                            }
                        }
                        url += ")";
                    }
                    break;
                //배터리 관련 url 문자열 생성
                case "배터리":
                    if (t)
                    {
                        url += "(";
                        string minb = DminBattery.Value+"";
                        string maxb = DmaxBattery.Value+"";
                        url += "배터리 >= '" + minb + "' and 배터리 < '" + maxb + "')";
                    }
                    break;
                //온도 관련 url 문자열 생성
                case "온도":
                    if (t)
                    {
                        url += "(";
                        string mint = DminTemp.Value + "";
                        string maxt = DmaxTemp.Value + "";
                        url += "온도 >= '" + mint + "' and 온도 < '" + maxt + "')";
                    }
                    break;
                //습도 관련 url 문자열 생성
                case "습도":
                    if (t)
                    {
                        url += "(";
                        string minh = DminHumi.Value + "";
                        string maxh = DmaxHumi.Value + "";
                        url += "습도 >= '" + minh + "' and 습도 < '" + maxh + "')";
                    }
                    break; 
            }
          
            return url;
        }


        /*------------------------------------------------------버튼 클릭 이벤트------------------------------------------------------*/
        /* 검색 버튼 클릭 이벤트 */
        private void DsearchButton_Click(object sender, System.EventArgs e)
        {
            update();
        }
        /* Company 전체 선택 버튼 클릭 이벤트 */
        private void DAcompanyCheckButton_Click(object sender, System.EventArgs e)
        {
            if (DcompanyCheckB.Enabled)
            {
                if (DCompany_Button_Check == false)
                {
                    DCompany_Button_Check = true;
                    for (int i = 0; i < DcompanyCheckB.Items.Count; i++)
                    {
                        DcompanyCheckB.SetItemChecked(i, true);
                    }
                }
                else
                {
                    DCompany_Button_Check = false;
                    for (int i = 0; i < DcompanyCheckB.Items.Count; i++)
                    {
                        DcompanyCheckB.SetItemChecked(i, false);
                    }
                }
            }
        }
        /* MAC 전체 선택 버튼 클릭 이벤트 */
        private void DAmacCheckButton_Click(object sender, System.EventArgs e)
        {
            if (DmacCheckB.Enabled)
            {
                if (DMac_Button_Check == false)
                {
                    DMac_Button_Check = true;
                    for (int i = 0; i < DmacCheckB.Items.Count; i++)
                    {
                        DmacCheckB.SetItemChecked(i, true);
                    }
                }
                else
                {
                    DMac_Button_Check = false;
                    for (int i = 0; i < DmacCheckB.Items.Count; i++)
                    {
                        DmacCheckB.SetItemChecked(i, false);
                    }
                }
            }
        }
        /* Show 전체 선택 버튼 클릭 이벤트 */
        private void GshowButton_Click(object sender, System.EventArgs e)
        {
            //그래픽 부분의 시간 UI가 활성화 되었거나 MAC 체크리스트를 하나라도 체크한 경우 Ginfo 업데이트
            if (GmacCheckB.CheckedItems.Count > 0 || GSdatePicker.Enabled)
            {
                string url = URLString.readinfo + " Where ";

                //MAC 체크리스트가 하나 이상 체크된 경우
                if (GmacCheckB.CheckedItems.Count > 0)
                {
                    url += "(";
                    for (int i = 0; i < GmacCheckB.CheckedItems.Count; i++)
                    {
                        url += "MAC = '" + GmacCheckB.CheckedItems[i] + "'";
                        if (i + 1 < GmacCheckB.CheckedItems.Count)
                        {
                            url += " or ";
                        }
                    }
                    url += ")";
                }

                //MAC 체크리스트가 하나 이상 체크되었고 시간 UI가 활성화 된 경우
                if (GmacCheckB.CheckedItems.Count > 0 && GSdatePicker.Enabled)
                {
                    url += " and ";
                }

                //시간 UI가 활성화 된 경우
                if (GSdatePicker.Enabled)
                {
                    url += "(";
                    //시작시간 가져오기
                    string sdate = GSdatePicker.Text + "-" + GStimePickerH.Value.ToString("00") + "-" + GStimePickerM.Value.ToString("00") + "-" + GStimePickerS.Value.ToString("00");
                    //종료시간 가져오기
                    string edate = GEdatePicker.Text + "-" + GEtimePickerH.Value.ToString("00") + "-" + GEtimePickerM.Value.ToString("00") + "-" + GEtimePickerS.Value.ToString("00");
                    url += "시간 >= '" + sdate + "' and 시간 < '" + edate + "')";
                }
                MessageBox.Show(url);
                Ginfo = initInfo(url, 6);
                ccc[0] = Color.Red;
                ccc[1] = Color.Green;
                ccc[2] = Color.Blue;

                double tmax = -130;
                double tmin = double.Parse(Ginfo[0].Temp);
                string tmac = Ginfo[0].Mac;
                List<int> index = new List<int>();
                int icheck = -1;
                DateTime tdmax = Ginfo[0].getTime();
                DateTime tdmin = Ginfo[0].getTime();

                foreach (Info inf in Ginfo)
                {
                    if (double.Parse(inf.Temp) > tmax)
                    {
                        tmax = double.Parse(inf.Temp);
                    }
                    if (double.Parse(inf.Temp) < tmin)
                    {
                        tmin = double.Parse(inf.Temp);
                    }
                    if (!inf.Mac.Equals(tmac))
                    {
                        tmac = inf.Mac;
                        index.Add(icheck);
                    }
                    if (DateTime.Compare(tdmin, inf.getTime()) > 0)
                    {
                        tdmin = inf.getTime();
                    }
                    if (DateTime.Compare(tdmax, inf.getTime()) < 0)
                    {
                        tdmax = inf.getTime();
                    }
                    icheck++;
                }
                index.Add(Ginfo.Count - 1);

                int xmax = 0;
                int xmin = 0;
                string timetype = "";

                if ((Ginfo[Ginfo.Count - 1].getTime().Year - Ginfo[0].getTime().Year) > 1)
                {
                    MessageBox.Show("yy 결과" + (Ginfo[Ginfo.Count - 1].getTime().Year - Ginfo[0].getTime().Year) + "");
                    timetype = "yy";
                   xmax = DateTime.Now.Year;
                    xmin = 2018;
                }
                else if ((Ginfo[Ginfo.Count - 1].getTime().Month - Ginfo[0].getTime().Month) > 1)
                {
                    MessageBox.Show("MM 결과" + (Ginfo[Ginfo.Count - 1].getTime().Month - Ginfo[0].getTime().Month) + "");
                    timetype = "MM";
                   xmax = 12;
                   xmin = 1;
                }
                else if ((Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalDays > 1)
                {
                    MessageBox.Show("dd 결과" + (Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalDays + "");
                    timetype = "dd";
                   xmax = 31;
                  xmin = 1;
                }
                else if ((Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalHours > 1)
                {
                    MessageBox.Show("hh 결과" + (Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalHours + "");
                    timetype = "hh";
                xmax = 24;
                xmin = 0;
                }
                else if ((Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalMinutes > 1)
                {
                    MessageBox.Show("mm 결과" + (Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalMinutes + "");
                    timetype = "mm";
                  xmax = 60;
                xmin = 0;
                }
                else
                {
                    MessageBox.Show("ss 결과" + (Ginfo[Ginfo.Count - 1].getTime() - Ginfo[0].getTime()).TotalSeconds + "");
                    timetype = "ss";
                      xmax = 60;
                     xmin = 0;
                }

                // chart2 컨트롤에 Collection에 있었던 내용을 삭제
                chart1.ChartAreas.Clear();
                chart1.Series.Clear();
                // chart2 컨트롤에 Collection에 있었던 내용을 삭제
                chart2.ChartAreas.Clear();
                chart2.Series.Clear();

                // ChartArea 추가
                chart1.ChartAreas.Add("Draw");
                chart1.ChartAreas["Draw"].BackColor = Color.Black;
                // ChartArea 추가
                chart2.ChartAreas.Add("Draw");
                chart2.ChartAreas["Draw"].BackColor = Color.Black;

                // ChartArea X축과 Y축을 설정
                int pm = 0;
                switch (timetype)
                {
                    case "yy":
                        pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Year;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Year + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Year;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Year + pm;
                        break;
                    case "MM":
                        if (tdmax.Month < 12)
                            pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Month;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Month + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Month;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Month + pm;
                        break;
                    case "dd":
                        if (tdmax.Day < 31)
                            pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Day;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Day + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Day;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Day + pm;
                        break;
                    case "hh":
                        if (tdmax.Hour < 24)
                            pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Hour;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Hour + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Hour;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Hour + pm;
                        break;
                    case "mm":
                        if (tdmax.Minute < 60)
                            pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Minute;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Minute + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Minute;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Minute + pm;
                        break;
                    case "ss":
                        if (tdmax.Second < 60)
                            pm = 1;
                        chart1.ChartAreas["Draw"].AxisX.Minimum = tdmin.Second;
                        chart1.ChartAreas["Draw"].AxisX.Maximum = tdmax.Second + pm;
                        chart2.ChartAreas["Draw"].AxisX.Minimum = tdmin.Second;
                        chart2.ChartAreas["Draw"].AxisX.Maximum = tdmax.Second + pm;
                        break;
                }
                chart1.ChartAreas["Draw"].AxisX.Interval = 1;
                chart1.ChartAreas["Draw"].AxisX.MajorGrid.LineColor = Color.Gray;
                chart1.ChartAreas["Draw"].AxisX.MajorGrid.LineDashStyle = ChartDashStyle.Dash;

                chart1.ChartAreas["Draw"].AxisY.Minimum = Convert.ToInt32(tmin / 10) * 10 - 10;
                chart1.ChartAreas["Draw"].AxisY.Maximum = Convert.ToInt32(tmax / 10) * 10 + 10;
                chart1.ChartAreas["Draw"].AxisY.Interval = 5;
                chart1.ChartAreas["Draw"].AxisY.MajorGrid.LineColor = Color.Gray;
                chart1.ChartAreas["Draw"].AxisY.MajorGrid.LineDashStyle = ChartDashStyle.Dash;


                chart2.ChartAreas["Draw"].AxisX.Interval = 1;
                chart2.ChartAreas["Draw"].AxisX.MajorGrid.LineColor = Color.Gray;
                chart2.ChartAreas["Draw"].AxisX.MajorGrid.LineDashStyle = ChartDashStyle.Dash;

                chart2.ChartAreas["Draw"].AxisY.Minimum = 0;
                chart2.ChartAreas["Draw"].AxisY.Maximum = 100;
                chart2.ChartAreas["Draw"].AxisY.Interval = 10;
                chart2.ChartAreas["Draw"].AxisY.MajorGrid.LineColor = Color.Gray;
                chart2.ChartAreas["Draw"].AxisY.MajorGrid.LineDashStyle = ChartDashStyle.Dash;

                for (int j = 0; j < index.Count; j++)
                {
                    // Series 추가(온도)      
                    chart1.Series.Add("온도" + j);
                    //chart1.Series["온도"+j].Color = ccc[j];
                    chart1.Series["온도" + j].ChartType = SeriesChartType.Line;
                    chart1.Series["온도" + j].BorderWidth = 2;
                    chart1.Series["온도" + j].LegendText = "Mac " + Ginfo[index[j]].Mac + "측정온도(°C)";

                    // Series 추가(습도)   
                    chart2.Series.Add("습도" + j);
                    //chart1.Series["습도" + j].Color = ccc[j];
                    chart2.Series["습도" + j].ChartType = SeriesChartType.Line;
                    chart2.Series["습도" + j].BorderWidth = 2;
                    chart2.Series["습도" + j].LegendText = "Mac " + Ginfo[index[j]].Mac + "측정습도( %)";

                    int i = 0;
                    if (j >= 1)
                    {
                        i = index[j - 1] + 1;
                    }

                    for (; i < index[j]; i++)
                    {
                        double temp;
                        switch (timetype)
                        {
                            case "yy":
                                temp = Convert.ToDouble(Ginfo[i].getTime().Year) + Convert.ToDouble(Ginfo[i].getTime().Month) / 100;
                                chart1.Series["온도" + j].Points.AddXY(temp, Ginfo[i].Temp);
                                chart2.Series["습도" + j].Points.AddXY(temp, Ginfo[i].Humi);
                                break;
                            case "MM":
                                temp = Convert.ToDouble(Ginfo[i].getTime().Month) + Convert.ToDouble(Ginfo[i].getTime().Day) / 100;
                                chart1.Series["온도" + j].Points.AddXY(temp, Ginfo[i].Temp);
                                chart2.Series["습도" + j].Points.AddXY(temp, Ginfo[i].Humi);
                                break;
                            case "dd":
                                temp = Convert.ToDouble(Ginfo[i].getTime().Day) + Convert.ToDouble(Ginfo[i].getTime().Hour) / 100;
                                chart1.Series["온도" + j].Points.AddXY(temp, Ginfo[i].Temp);
                                chart2.Series["습도" + j].Points.AddXY(temp, Ginfo[i].Humi);
                                break;
                            case "hh":
                                temp = Convert.ToDouble(Ginfo[i].getTime().Hour) + Convert.ToDouble(Ginfo[i].getTime().Minute) / 100;
                                chart1.Series["온도" + j].Points.AddXY(temp, Ginfo[i].Temp);
                                chart2.Series["습도" + j].Points.AddXY(temp, Ginfo[i].Humi);
                                //MessageBox.Show(i + "번째 반복, x축 값 : " + temp + ", y축 값 : " + double.Parse(Ginfo[i].Temp));
                                break;
                            case "mm":
                                temp = Convert.ToDouble(Ginfo[i].getTime().Minute) + Convert.ToDouble(Ginfo[i].getTime().Second) / 100;
                                chart1.Series["온도" + j].Points.AddXY(temp, Ginfo[i].Temp);
                                chart2.Series["습도" + j].Points.AddXY(temp, Ginfo[i].Humi);
                                break;
                            case "ss":
                                chart1.Series["온도" + j].Points.AddXY(Ginfo[i].getTime().Second, double.Parse(Ginfo[i].Temp));
                                chart2.Series["습도" + j].Points.AddXY(Ginfo[i].getTime().Second, Ginfo[i].Humi);
                                break;
                        }
                        
                    }
                }
            }     
        }
        /* 그래프 부분 MAC 전체 선택 버튼 클릭 이벤트 */
        private void GAmacCheckButton_Click(object sender, System.EventArgs e)
        {
            if (GmacCheckB.Enabled)
            {
                if (GMac_Button_Check == false)
                {
                    GMac_Button_Check = true;
                    for (int i = 0; i < GmacCheckB.Items.Count; i++)
                    {
                        GmacCheckB.SetItemChecked(i, true);
                    }
                }
                else
                {
                    GMac_Button_Check = false;
                    for (int i = 0; i < GmacCheckB.Items.Count; i++)
                    {
                        GmacCheckB.SetItemChecked(i, false);
                    }
                }
            }
        }

        /*----------------------------------------------------체크 박스 허용 이벤트----------------------------------------------------*/
        /*  회사명 체크 박스 허용 이벤트    */
        private void DcompanyCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if(DcompanyCheck.Checked)
            {
                DcompanyCheckB.Enabled = true;
            }
            else
            {
                DcompanyCheckB.Enabled = false;
            }
        }
        /*  온도 체크 박스 허용 이벤트 */
        private void DtempCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if (DtempCheck.Checked)
            {
                DminTemp.Enabled = true;
                DmaxTemp.Enabled = true;
            }
            else
            {
                DminTemp.Enabled = false;
                DmaxTemp.Enabled = false;
            }
        }
        /*  습도 체크 박스 허용 이벤트 */
        private void DhumiCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if (DhumiCheck.Checked)
            {
                DminHumi.Enabled = true;
                DmaxHumi.Enabled = true;
            }
            else
            {
                DminHumi.Enabled = false;
                DmaxHumi.Enabled = false;
            }
        }
        /* 배터리 체크 박스 허용 이벤트 */
        private void DbatteryCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if (DbatteryCheck.Checked)
            {
                DminBattery.Enabled = true;
                DmaxBattery.Enabled = true;
            }
            else
            {
                DminBattery.Enabled = false;
                DmaxBattery.Enabled = false;
            }
        }
        /* 시간 체크 박스 허용 이벤트 */
        private void DtimeCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if (DtimeCheck.Checked)
            {
                DSdatePicker.Enabled = true;
                DStimePickerH.Enabled = true;
                DStimePickerM.Enabled = true;
                DStimePickerS.Enabled = true;
                DEdatePicker.Enabled = true;
                DEtimePickerH.Enabled = true;
                DEtimePickerM.Enabled = true;
                DEtimePickerS.Enabled = true;
            }
            else
            {
                DSdatePicker.Enabled = false;
                DStimePickerH.Enabled = false;
                DStimePickerM.Enabled = false;
                DStimePickerS.Enabled = false;
                DEdatePicker.Enabled = false;
                DEtimePickerH.Enabled = false;
                DEtimePickerM.Enabled = false;
                DEtimePickerS.Enabled = false;
            }
        }
        /* 그래픽 부분 시간 체크 박스 허용 이벤트 */
        private void GtimeCheck_CheckedChanged(object sender, System.EventArgs e)
        {
            if (GtimeCheck.Checked)
            {
                GSdatePicker.Enabled = true;
                GStimePickerH.Enabled = true;
                GStimePickerM.Enabled = true;
                GStimePickerS.Enabled = true;
                GEdatePicker.Enabled = true;
                GEtimePickerH.Enabled = true;
                GEtimePickerM.Enabled = true;
                GEtimePickerS.Enabled = true;
            }
            else
            {
                GSdatePicker.Enabled = false;
                GStimePickerH.Enabled = false;
                GStimePickerM.Enabled = false;
                GStimePickerS.Enabled = false;
                GEdatePicker.Enabled = false;
                GEtimePickerH.Enabled = false;
                GEtimePickerM.Enabled = false;
                GEtimePickerS.Enabled = false;
            }
        }
    }
}

      