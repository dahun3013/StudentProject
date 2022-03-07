namespace WindowsFormsApplication1
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다.
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마십시오.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea2 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend2 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series2 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.DsearchButton = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.DcompanyCheck = new System.Windows.Forms.CheckBox();
            this.DtimeCheck = new System.Windows.Forms.CheckBox();
            this.DbatteryCheck = new System.Windows.Forms.CheckBox();
            this.DhumiCheck = new System.Windows.Forms.CheckBox();
            this.DtempCheck = new System.Windows.Forms.CheckBox();
            this.label17 = new System.Windows.Forms.Label();
            this.label18 = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.label16 = new System.Windows.Forms.Label();
            this.label15 = new System.Windows.Forms.Label();
            this.label14 = new System.Windows.Forms.Label();
            this.DEtimePickerS = new System.Windows.Forms.NumericUpDown();
            this.DEtimePickerM = new System.Windows.Forms.NumericUpDown();
            this.DEtimePickerH = new System.Windows.Forms.NumericUpDown();
            this.DEdatePicker = new System.Windows.Forms.DateTimePicker();
            this.DStimePickerS = new System.Windows.Forms.NumericUpDown();
            this.DStimePickerM = new System.Windows.Forms.NumericUpDown();
            this.DStimePickerH = new System.Windows.Forms.NumericUpDown();
            this.DmaxBattery = new System.Windows.Forms.NumericUpDown();
            this.DmaxHumi = new System.Windows.Forms.NumericUpDown();
            this.DmaxTemp = new System.Windows.Forms.NumericUpDown();
            this.DminBattery = new System.Windows.Forms.NumericUpDown();
            this.DminHumi = new System.Windows.Forms.NumericUpDown();
            this.DminTemp = new System.Windows.Forms.NumericUpDown();
            this.DSdatePicker = new System.Windows.Forms.DateTimePicker();
            this.DAcompanyCheckButton = new System.Windows.Forms.Button();
            this.DAmacCheckButton = new System.Windows.Forms.Button();
            this.DcompanyCheckB = new System.Windows.Forms.CheckedListBox();
            this.DmacCheckB = new System.Windows.Forms.CheckedListBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.GAmacCheckButton = new System.Windows.Forms.Button();
            this.GtimeCheck = new System.Windows.Forms.CheckBox();
            this.label12 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.label20 = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.label22 = new System.Windows.Forms.Label();
            this.label23 = new System.Windows.Forms.Label();
            this.GEtimePickerS = new System.Windows.Forms.NumericUpDown();
            this.GEtimePickerM = new System.Windows.Forms.NumericUpDown();
            this.GEtimePickerH = new System.Windows.Forms.NumericUpDown();
            this.GEdatePicker = new System.Windows.Forms.DateTimePicker();
            this.GStimePickerS = new System.Windows.Forms.NumericUpDown();
            this.GStimePickerM = new System.Windows.Forms.NumericUpDown();
            this.GStimePickerH = new System.Windows.Forms.NumericUpDown();
            this.GSdatePicker = new System.Windows.Forms.DateTimePicker();
            this.label24 = new System.Windows.Forms.Label();
            this.label25 = new System.Windows.Forms.Label();
            this.GshowButton = new System.Windows.Forms.Button();
            this.label11 = new System.Windows.Forms.Label();
            this.GmacCheckB = new System.Windows.Forms.CheckedListBox();
            this.chart2 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.chart1 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerM)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerH)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerM)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerH)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxBattery)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxHumi)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxTemp)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminBattery)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminHumi)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminTemp)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerM)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerH)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerM)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerH)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(3, 224);
            this.dataGridView1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 27;
            this.dataGridView1.Size = new System.Drawing.Size(1001, 693);
            this.dataGridView1.TabIndex = 0;
            // 
            // DsearchButton
            // 
            this.DsearchButton.Location = new System.Drawing.Point(790, 175);
            this.DsearchButton.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.DsearchButton.Name = "DsearchButton";
            this.DsearchButton.Size = new System.Drawing.Size(165, 42);
            this.DsearchButton.TabIndex = 3;
            this.DsearchButton.Text = "Search";
            this.DsearchButton.UseVisualStyleBackColor = true;
            this.DsearchButton.Click += new System.EventHandler(this.DsearchButton_Click);
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.Silver;
            this.panel1.Controls.Add(this.DcompanyCheck);
            this.panel1.Controls.Add(this.DtimeCheck);
            this.panel1.Controls.Add(this.DbatteryCheck);
            this.panel1.Controls.Add(this.DhumiCheck);
            this.panel1.Controls.Add(this.DtempCheck);
            this.panel1.Controls.Add(this.label17);
            this.panel1.Controls.Add(this.label18);
            this.panel1.Controls.Add(this.label19);
            this.panel1.Controls.Add(this.label16);
            this.panel1.Controls.Add(this.label15);
            this.panel1.Controls.Add(this.label14);
            this.panel1.Controls.Add(this.DEtimePickerS);
            this.panel1.Controls.Add(this.DEtimePickerM);
            this.panel1.Controls.Add(this.DEtimePickerH);
            this.panel1.Controls.Add(this.DEdatePicker);
            this.panel1.Controls.Add(this.DStimePickerS);
            this.panel1.Controls.Add(this.DStimePickerM);
            this.panel1.Controls.Add(this.DStimePickerH);
            this.panel1.Controls.Add(this.DmaxBattery);
            this.panel1.Controls.Add(this.DmaxHumi);
            this.panel1.Controls.Add(this.DmaxTemp);
            this.panel1.Controls.Add(this.DminBattery);
            this.panel1.Controls.Add(this.DminHumi);
            this.panel1.Controls.Add(this.DminTemp);
            this.panel1.Controls.Add(this.DSdatePicker);
            this.panel1.Controls.Add(this.DAcompanyCheckButton);
            this.panel1.Controls.Add(this.DAmacCheckButton);
            this.panel1.Controls.Add(this.DcompanyCheckB);
            this.panel1.Controls.Add(this.DmacCheckB);
            this.panel1.Controls.Add(this.label9);
            this.panel1.Controls.Add(this.label10);
            this.panel1.Controls.Add(this.label8);
            this.panel1.Controls.Add(this.label7);
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.label5);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.dataGridView1);
            this.panel1.Controls.Add(this.DsearchButton);
            this.panel1.Location = new System.Drawing.Point(11, 12);
            this.panel1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1007, 921);
            this.panel1.TabIndex = 4;
            // 
            // DcompanyCheck
            // 
            this.DcompanyCheck.AutoSize = true;
            this.DcompanyCheck.Location = new System.Drawing.Point(401, 188);
            this.DcompanyCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DcompanyCheck.Name = "DcompanyCheck";
            this.DcompanyCheck.Size = new System.Drawing.Size(91, 19);
            this.DcompanyCheck.TabIndex = 73;
            this.DcompanyCheck.Text = "Company";
            this.DcompanyCheck.UseVisualStyleBackColor = true;
            this.DcompanyCheck.CheckedChanged += new System.EventHandler(this.DcompanyCheck_CheckedChanged);
            // 
            // DtimeCheck
            // 
            this.DtimeCheck.AutoSize = true;
            this.DtimeCheck.Location = new System.Drawing.Point(717, 188);
            this.DtimeCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DtimeCheck.Name = "DtimeCheck";
            this.DtimeCheck.Size = new System.Drawing.Size(59, 19);
            this.DtimeCheck.TabIndex = 72;
            this.DtimeCheck.Text = "Time";
            this.DtimeCheck.UseVisualStyleBackColor = true;
            this.DtimeCheck.CheckedChanged += new System.EventHandler(this.DtimeCheck_CheckedChanged);
            // 
            // DbatteryCheck
            // 
            this.DbatteryCheck.AutoSize = true;
            this.DbatteryCheck.Location = new System.Drawing.Point(638, 188);
            this.DbatteryCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DbatteryCheck.Name = "DbatteryCheck";
            this.DbatteryCheck.Size = new System.Drawing.Size(75, 19);
            this.DbatteryCheck.TabIndex = 71;
            this.DbatteryCheck.Text = "Battery";
            this.DbatteryCheck.UseVisualStyleBackColor = true;
            this.DbatteryCheck.CheckedChanged += new System.EventHandler(this.DbatteryCheck_CheckedChanged);
            // 
            // DhumiCheck
            // 
            this.DhumiCheck.AutoSize = true;
            this.DhumiCheck.Location = new System.Drawing.Point(570, 188);
            this.DhumiCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DhumiCheck.Name = "DhumiCheck";
            this.DhumiCheck.Size = new System.Drawing.Size(61, 19);
            this.DhumiCheck.TabIndex = 70;
            this.DhumiCheck.Text = "Humi";
            this.DhumiCheck.UseVisualStyleBackColor = true;
            this.DhumiCheck.CheckedChanged += new System.EventHandler(this.DhumiCheck_CheckedChanged);
            // 
            // DtempCheck
            // 
            this.DtempCheck.AutoSize = true;
            this.DtempCheck.Location = new System.Drawing.Point(498, 188);
            this.DtempCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DtempCheck.Name = "DtempCheck";
            this.DtempCheck.Size = new System.Drawing.Size(64, 19);
            this.DtempCheck.TabIndex = 69;
            this.DtempCheck.Text = "Temp";
            this.DtempCheck.UseVisualStyleBackColor = true;
            this.DtempCheck.CheckedChanged += new System.EventHandler(this.DtempCheck_CheckedChanged);
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(903, 62);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(58, 15);
            this.label17.TabIndex = 68;
            this.label17.Text = "Second";
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Location = new System.Drawing.Point(845, 62);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(50, 15);
            this.label18.TabIndex = 67;
            this.label18.Text = "Minute";
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Location = new System.Drawing.Point(787, 62);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(38, 15);
            this.label19.TabIndex = 66;
            this.label19.Text = "Hour";
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(903, 4);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(58, 15);
            this.label16.TabIndex = 65;
            this.label16.Text = "Second";
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(845, 4);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(50, 15);
            this.label15.TabIndex = 64;
            this.label15.Text = "Minute";
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(787, 4);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(38, 15);
            this.label14.TabIndex = 63;
            this.label14.Text = "Hour";
            // 
            // DEtimePickerS
            // 
            this.DEtimePickerS.Enabled = false;
            this.DEtimePickerS.Location = new System.Drawing.Point(905, 81);
            this.DEtimePickerS.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DEtimePickerS.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.DEtimePickerS.Name = "DEtimePickerS";
            this.DEtimePickerS.Size = new System.Drawing.Size(51, 25);
            this.DEtimePickerS.TabIndex = 62;
            // 
            // DEtimePickerM
            // 
            this.DEtimePickerM.Enabled = false;
            this.DEtimePickerM.Location = new System.Drawing.Point(847, 81);
            this.DEtimePickerM.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DEtimePickerM.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.DEtimePickerM.Name = "DEtimePickerM";
            this.DEtimePickerM.Size = new System.Drawing.Size(51, 25);
            this.DEtimePickerM.TabIndex = 61;
            // 
            // DEtimePickerH
            // 
            this.DEtimePickerH.Enabled = false;
            this.DEtimePickerH.Location = new System.Drawing.Point(790, 81);
            this.DEtimePickerH.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DEtimePickerH.Maximum = new decimal(new int[] {
            24,
            0,
            0,
            0});
            this.DEtimePickerH.Name = "DEtimePickerH";
            this.DEtimePickerH.Size = new System.Drawing.Size(51, 25);
            this.DEtimePickerH.TabIndex = 60;
            // 
            // DEdatePicker
            // 
            this.DEdatePicker.Enabled = false;
            this.DEdatePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.DEdatePicker.Location = new System.Drawing.Point(648, 81);
            this.DEdatePicker.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DEdatePicker.Name = "DEdatePicker";
            this.DEdatePicker.Size = new System.Drawing.Size(140, 25);
            this.DEdatePicker.TabIndex = 59;
            // 
            // DStimePickerS
            // 
            this.DStimePickerS.Enabled = false;
            this.DStimePickerS.Location = new System.Drawing.Point(903, 22);
            this.DStimePickerS.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DStimePickerS.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.DStimePickerS.Name = "DStimePickerS";
            this.DStimePickerS.Size = new System.Drawing.Size(51, 25);
            this.DStimePickerS.TabIndex = 58;
            // 
            // DStimePickerM
            // 
            this.DStimePickerM.Enabled = false;
            this.DStimePickerM.Location = new System.Drawing.Point(845, 22);
            this.DStimePickerM.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DStimePickerM.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.DStimePickerM.Name = "DStimePickerM";
            this.DStimePickerM.Size = new System.Drawing.Size(51, 25);
            this.DStimePickerM.TabIndex = 57;
            // 
            // DStimePickerH
            // 
            this.DStimePickerH.Enabled = false;
            this.DStimePickerH.Location = new System.Drawing.Point(787, 22);
            this.DStimePickerH.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DStimePickerH.Maximum = new decimal(new int[] {
            24,
            0,
            0,
            0});
            this.DStimePickerH.Name = "DStimePickerH";
            this.DStimePickerH.Size = new System.Drawing.Size(51, 25);
            this.DStimePickerH.TabIndex = 56;
            // 
            // DmaxBattery
            // 
            this.DmaxBattery.Enabled = false;
            this.DmaxBattery.Location = new System.Drawing.Point(483, 138);
            this.DmaxBattery.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DmaxBattery.Name = "DmaxBattery";
            this.DmaxBattery.Size = new System.Drawing.Size(137, 25);
            this.DmaxBattery.TabIndex = 53;
            // 
            // DmaxHumi
            // 
            this.DmaxHumi.DecimalPlaces = 1;
            this.DmaxHumi.Enabled = false;
            this.DmaxHumi.Location = new System.Drawing.Point(483, 80);
            this.DmaxHumi.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DmaxHumi.Name = "DmaxHumi";
            this.DmaxHumi.Size = new System.Drawing.Size(137, 25);
            this.DmaxHumi.TabIndex = 52;
            // 
            // DmaxTemp
            // 
            this.DmaxTemp.DecimalPlaces = 1;
            this.DmaxTemp.Enabled = false;
            this.DmaxTemp.Location = new System.Drawing.Point(483, 25);
            this.DmaxTemp.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DmaxTemp.Maximum = new decimal(new int[] {
            120,
            0,
            0,
            0});
            this.DmaxTemp.Minimum = new decimal(new int[] {
            120,
            0,
            0,
            -2147483648});
            this.DmaxTemp.Name = "DmaxTemp";
            this.DmaxTemp.Size = new System.Drawing.Size(137, 25);
            this.DmaxTemp.TabIndex = 51;
            // 
            // DminBattery
            // 
            this.DminBattery.Enabled = false;
            this.DminBattery.Location = new System.Drawing.Point(336, 138);
            this.DminBattery.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DminBattery.Name = "DminBattery";
            this.DminBattery.Size = new System.Drawing.Size(137, 25);
            this.DminBattery.TabIndex = 50;
            // 
            // DminHumi
            // 
            this.DminHumi.DecimalPlaces = 1;
            this.DminHumi.Enabled = false;
            this.DminHumi.Location = new System.Drawing.Point(336, 80);
            this.DminHumi.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DminHumi.Name = "DminHumi";
            this.DminHumi.Size = new System.Drawing.Size(137, 25);
            this.DminHumi.TabIndex = 49;
            // 
            // DminTemp
            // 
            this.DminTemp.DecimalPlaces = 1;
            this.DminTemp.Enabled = false;
            this.DminTemp.Location = new System.Drawing.Point(336, 25);
            this.DminTemp.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DminTemp.Maximum = new decimal(new int[] {
            120,
            0,
            0,
            0});
            this.DminTemp.Minimum = new decimal(new int[] {
            120,
            0,
            0,
            -2147483648});
            this.DminTemp.Name = "DminTemp";
            this.DminTemp.Size = new System.Drawing.Size(137, 25);
            this.DminTemp.TabIndex = 47;
            // 
            // DSdatePicker
            // 
            this.DSdatePicker.Enabled = false;
            this.DSdatePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.DSdatePicker.Location = new System.Drawing.Point(646, 22);
            this.DSdatePicker.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DSdatePicker.Name = "DSdatePicker";
            this.DSdatePicker.Size = new System.Drawing.Size(140, 25);
            this.DSdatePicker.TabIndex = 38;
            // 
            // DAcompanyCheckButton
            // 
            this.DAcompanyCheckButton.Location = new System.Drawing.Point(167, 188);
            this.DAcompanyCheckButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DAcompanyCheckButton.Name = "DAcompanyCheckButton";
            this.DAcompanyCheckButton.Size = new System.Drawing.Size(153, 26);
            this.DAcompanyCheckButton.TabIndex = 37;
            this.DAcompanyCheckButton.Text = "All Check";
            this.DAcompanyCheckButton.UseVisualStyleBackColor = true;
            this.DAcompanyCheckButton.Click += new System.EventHandler(this.DAcompanyCheckButton_Click);
            // 
            // DAmacCheckButton
            // 
            this.DAmacCheckButton.Location = new System.Drawing.Point(7, 188);
            this.DAmacCheckButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DAmacCheckButton.Name = "DAmacCheckButton";
            this.DAmacCheckButton.Size = new System.Drawing.Size(153, 26);
            this.DAmacCheckButton.TabIndex = 36;
            this.DAmacCheckButton.Text = "All Check";
            this.DAmacCheckButton.UseVisualStyleBackColor = true;
            this.DAmacCheckButton.Click += new System.EventHandler(this.DAmacCheckButton_Click);
            // 
            // DcompanyCheckB
            // 
            this.DcompanyCheckB.Enabled = false;
            this.DcompanyCheckB.FormattingEnabled = true;
            this.DcompanyCheckB.Items.AddRange(new object[] {
            "Innobase"});
            this.DcompanyCheckB.Location = new System.Drawing.Point(167, 22);
            this.DcompanyCheckB.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DcompanyCheckB.Name = "DcompanyCheckB";
            this.DcompanyCheckB.Size = new System.Drawing.Size(153, 164);
            this.DcompanyCheckB.TabIndex = 35;
            // 
            // DmacCheckB
            // 
            this.DmacCheckB.FormattingEnabled = true;
            this.DmacCheckB.Location = new System.Drawing.Point(7, 22);
            this.DmacCheckB.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.DmacCheckB.Name = "DmacCheckB";
            this.DmacCheckB.Size = new System.Drawing.Size(153, 164);
            this.DmacCheckB.TabIndex = 34;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(519, 115);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(82, 15);
            this.label9.TabIndex = 29;
            this.label9.Text = "MaxBattery";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(374, 115);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(76, 15);
            this.label10.TabIndex = 28;
            this.label10.Text = "MinBattery";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(519, 61);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(68, 15);
            this.label8.TabIndex = 19;
            this.label8.Text = "MaxHumi";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(374, 61);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(62, 15);
            this.label7.TabIndex = 18;
            this.label7.Text = "MinHumi";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(519, 4);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(71, 15);
            this.label6.TabIndex = 17;
            this.label6.Text = "MaxTemp";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(374, 4);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(65, 15);
            this.label5.TabIndex = 16;
            this.label5.Text = "MinTemp";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(646, 62);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(62, 15);
            this.label4.TabIndex = 7;
            this.label4.Text = "EndTime";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(646, 4);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(67, 15);
            this.label3.TabIndex = 6;
            this.label3.Text = "StartTime";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(207, 4);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(69, 15);
            this.label2.TabIndex = 5;
            this.label2.Text = "Company";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(61, 4);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(38, 15);
            this.label1.TabIndex = 4;
            this.label1.Text = "MAC";
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.LightGray;
            this.panel2.Controls.Add(this.GAmacCheckButton);
            this.panel2.Controls.Add(this.GtimeCheck);
            this.panel2.Controls.Add(this.label12);
            this.panel2.Controls.Add(this.label13);
            this.panel2.Controls.Add(this.label20);
            this.panel2.Controls.Add(this.label21);
            this.panel2.Controls.Add(this.label22);
            this.panel2.Controls.Add(this.label23);
            this.panel2.Controls.Add(this.GEtimePickerS);
            this.panel2.Controls.Add(this.GEtimePickerM);
            this.panel2.Controls.Add(this.GEtimePickerH);
            this.panel2.Controls.Add(this.GEdatePicker);
            this.panel2.Controls.Add(this.GStimePickerS);
            this.panel2.Controls.Add(this.GStimePickerM);
            this.panel2.Controls.Add(this.GStimePickerH);
            this.panel2.Controls.Add(this.GSdatePicker);
            this.panel2.Controls.Add(this.label24);
            this.panel2.Controls.Add(this.label25);
            this.panel2.Controls.Add(this.GshowButton);
            this.panel2.Controls.Add(this.label11);
            this.panel2.Controls.Add(this.GmacCheckB);
            this.panel2.Controls.Add(this.chart2);
            this.panel2.Controls.Add(this.chart1);
            this.panel2.Location = new System.Drawing.Point(1025, 12);
            this.panel2.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(641, 921);
            this.panel2.TabIndex = 5;
            // 
            // GAmacCheckButton
            // 
            this.GAmacCheckButton.Location = new System.Drawing.Point(5, 190);
            this.GAmacCheckButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GAmacCheckButton.Name = "GAmacCheckButton";
            this.GAmacCheckButton.Size = new System.Drawing.Size(153, 26);
            this.GAmacCheckButton.TabIndex = 74;
            this.GAmacCheckButton.Text = "All Check";
            this.GAmacCheckButton.UseVisualStyleBackColor = true;
            this.GAmacCheckButton.Click += new System.EventHandler(this.GAmacCheckButton_Click);
            // 
            // GtimeCheck
            // 
            this.GtimeCheck.AutoSize = true;
            this.GtimeCheck.Location = new System.Drawing.Point(515, 86);
            this.GtimeCheck.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GtimeCheck.Name = "GtimeCheck";
            this.GtimeCheck.Size = new System.Drawing.Size(59, 19);
            this.GtimeCheck.TabIndex = 74;
            this.GtimeCheck.Text = "Time";
            this.GtimeCheck.UseVisualStyleBackColor = true;
            this.GtimeCheck.CheckedChanged += new System.EventHandler(this.GtimeCheck_CheckedChanged);
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(437, 61);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(58, 15);
            this.label12.TabIndex = 84;
            this.label12.Text = "Second";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(379, 61);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(50, 15);
            this.label13.TabIndex = 83;
            this.label13.Text = "Minute";
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Location = new System.Drawing.Point(322, 61);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(38, 15);
            this.label20.TabIndex = 82;
            this.label20.Text = "Hour";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Location = new System.Drawing.Point(437, 2);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(58, 15);
            this.label21.TabIndex = 81;
            this.label21.Text = "Second";
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Location = new System.Drawing.Point(379, 2);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(50, 15);
            this.label22.TabIndex = 80;
            this.label22.Text = "Minute";
            // 
            // label23
            // 
            this.label23.AutoSize = true;
            this.label23.Location = new System.Drawing.Point(322, 2);
            this.label23.Name = "label23";
            this.label23.Size = new System.Drawing.Size(38, 15);
            this.label23.TabIndex = 79;
            this.label23.Text = "Hour";
            // 
            // GEtimePickerS
            // 
            this.GEtimePickerS.Enabled = false;
            this.GEtimePickerS.Location = new System.Drawing.Point(440, 80);
            this.GEtimePickerS.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GEtimePickerS.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.GEtimePickerS.Name = "GEtimePickerS";
            this.GEtimePickerS.Size = new System.Drawing.Size(51, 25);
            this.GEtimePickerS.TabIndex = 78;
            // 
            // GEtimePickerM
            // 
            this.GEtimePickerM.Enabled = false;
            this.GEtimePickerM.Location = new System.Drawing.Point(381, 80);
            this.GEtimePickerM.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GEtimePickerM.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.GEtimePickerM.Name = "GEtimePickerM";
            this.GEtimePickerM.Size = new System.Drawing.Size(51, 25);
            this.GEtimePickerM.TabIndex = 77;
            // 
            // GEtimePickerH
            // 
            this.GEtimePickerH.Enabled = false;
            this.GEtimePickerH.Location = new System.Drawing.Point(324, 80);
            this.GEtimePickerH.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GEtimePickerH.Maximum = new decimal(new int[] {
            24,
            0,
            0,
            0});
            this.GEtimePickerH.Name = "GEtimePickerH";
            this.GEtimePickerH.Size = new System.Drawing.Size(51, 25);
            this.GEtimePickerH.TabIndex = 76;
            // 
            // GEdatePicker
            // 
            this.GEdatePicker.Enabled = false;
            this.GEdatePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.GEdatePicker.Location = new System.Drawing.Point(183, 80);
            this.GEdatePicker.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GEdatePicker.Name = "GEdatePicker";
            this.GEdatePicker.Size = new System.Drawing.Size(140, 25);
            this.GEdatePicker.TabIndex = 75;
            // 
            // GStimePickerS
            // 
            this.GStimePickerS.Enabled = false;
            this.GStimePickerS.Location = new System.Drawing.Point(437, 21);
            this.GStimePickerS.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GStimePickerS.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.GStimePickerS.Name = "GStimePickerS";
            this.GStimePickerS.Size = new System.Drawing.Size(51, 25);
            this.GStimePickerS.TabIndex = 74;
            // 
            // GStimePickerM
            // 
            this.GStimePickerM.Enabled = false;
            this.GStimePickerM.Location = new System.Drawing.Point(379, 21);
            this.GStimePickerM.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GStimePickerM.Maximum = new decimal(new int[] {
            59,
            0,
            0,
            0});
            this.GStimePickerM.Name = "GStimePickerM";
            this.GStimePickerM.Size = new System.Drawing.Size(51, 25);
            this.GStimePickerM.TabIndex = 73;
            // 
            // GStimePickerH
            // 
            this.GStimePickerH.Enabled = false;
            this.GStimePickerH.Location = new System.Drawing.Point(322, 21);
            this.GStimePickerH.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GStimePickerH.Maximum = new decimal(new int[] {
            24,
            0,
            0,
            0});
            this.GStimePickerH.Name = "GStimePickerH";
            this.GStimePickerH.Size = new System.Drawing.Size(51, 25);
            this.GStimePickerH.TabIndex = 72;
            // 
            // GSdatePicker
            // 
            this.GSdatePicker.Enabled = false;
            this.GSdatePicker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.GSdatePicker.Location = new System.Drawing.Point(180, 21);
            this.GSdatePicker.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GSdatePicker.Name = "GSdatePicker";
            this.GSdatePicker.Size = new System.Drawing.Size(140, 25);
            this.GSdatePicker.TabIndex = 71;
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Location = new System.Drawing.Point(180, 61);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(62, 15);
            this.label24.TabIndex = 70;
            this.label24.Text = "EndTime";
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Location = new System.Drawing.Point(180, 2);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(67, 15);
            this.label25.TabIndex = 69;
            this.label25.Text = "StartTime";
            // 
            // GshowButton
            // 
            this.GshowButton.Location = new System.Drawing.Point(183, 152);
            this.GshowButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GshowButton.Name = "GshowButton";
            this.GshowButton.Size = new System.Drawing.Size(421, 62);
            this.GshowButton.TabIndex = 42;
            this.GshowButton.Text = "GraphShow";
            this.GshowButton.UseVisualStyleBackColor = true;
            this.GshowButton.Click += new System.EventHandler(this.GshowButton_Click);
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(53, 3);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(38, 15);
            this.label11.TabIndex = 4;
            this.label11.Text = "MAC";
            // 
            // GmacCheckB
            // 
            this.GmacCheckB.FormattingEnabled = true;
            this.GmacCheckB.Location = new System.Drawing.Point(5, 22);
            this.GmacCheckB.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.GmacCheckB.Name = "GmacCheckB";
            this.GmacCheckB.Size = new System.Drawing.Size(153, 164);
            this.GmacCheckB.TabIndex = 3;
            // 
            // chart2
            // 
            chartArea1.Name = "ChartArea1";
            this.chart2.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chart2.Legends.Add(legend1);
            this.chart2.Location = new System.Drawing.Point(3, 572);
            this.chart2.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.chart2.Name = "chart2";
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "Series1";
            this.chart2.Series.Add(series1);
            this.chart2.Size = new System.Drawing.Size(634, 345);
            this.chart2.TabIndex = 2;
            this.chart2.Text = "chart2";
            // 
            // chart1
            // 
            chartArea2.Name = "ChartArea1";
            this.chart1.ChartAreas.Add(chartArea2);
            legend2.Name = "Legend1";
            this.chart1.Legends.Add(legend2);
            this.chart1.Location = new System.Drawing.Point(3, 224);
            this.chart1.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.chart1.Name = "chart1";
            series2.ChartArea = "ChartArea1";
            series2.Legend = "Legend1";
            series2.Name = "Series1";
            this.chart1.Series.Add(series2);
            this.chart1.Size = new System.Drawing.Size(634, 345);
            this.chart1.TabIndex = 1;
            this.chart1.Text = "chart1";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1674, 940);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Innobase_TeggView";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerM)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DEtimePickerH)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerM)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DStimePickerH)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxBattery)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxHumi)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DmaxTemp)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminBattery)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminHumi)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DminTemp)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerM)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GEtimePickerH)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerM)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GStimePickerH)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button DsearchButton;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart1;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart2;
        private System.Windows.Forms.CheckedListBox GmacCheckB;
        private System.Windows.Forms.CheckedListBox DmacCheckB;
        private System.Windows.Forms.CheckedListBox DcompanyCheckB;
        private System.Windows.Forms.Button GshowButton;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Button DAcompanyCheckButton;
        private System.Windows.Forms.Button DAmacCheckButton;
        private System.Windows.Forms.DateTimePicker DSdatePicker;
        private System.Windows.Forms.NumericUpDown DminTemp;
        private System.Windows.Forms.NumericUpDown DminHumi;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.NumericUpDown DEtimePickerS;
        private System.Windows.Forms.NumericUpDown DEtimePickerM;
        private System.Windows.Forms.NumericUpDown DEtimePickerH;
        private System.Windows.Forms.DateTimePicker DEdatePicker;
        private System.Windows.Forms.NumericUpDown DStimePickerS;
        private System.Windows.Forms.NumericUpDown DStimePickerM;
        private System.Windows.Forms.NumericUpDown DStimePickerH;
        private System.Windows.Forms.NumericUpDown DmaxBattery;
        private System.Windows.Forms.NumericUpDown DmaxHumi;
        private System.Windows.Forms.NumericUpDown DmaxTemp;
        private System.Windows.Forms.NumericUpDown DminBattery;
        private System.Windows.Forms.CheckBox DcompanyCheck;
        private System.Windows.Forms.CheckBox DtimeCheck;
        private System.Windows.Forms.CheckBox DbatteryCheck;
        private System.Windows.Forms.CheckBox DhumiCheck;
        private System.Windows.Forms.CheckBox DtempCheck;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.Label label22;
        private System.Windows.Forms.Label label23;
        private System.Windows.Forms.NumericUpDown GEtimePickerS;
        private System.Windows.Forms.NumericUpDown GEtimePickerM;
        private System.Windows.Forms.NumericUpDown GEtimePickerH;
        private System.Windows.Forms.DateTimePicker GEdatePicker;
        private System.Windows.Forms.NumericUpDown GStimePickerS;
        private System.Windows.Forms.NumericUpDown GStimePickerM;
        private System.Windows.Forms.NumericUpDown GStimePickerH;
        private System.Windows.Forms.DateTimePicker GSdatePicker;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.CheckBox GtimeCheck;
        private System.Windows.Forms.Button GAmacCheckButton;
    }
}

