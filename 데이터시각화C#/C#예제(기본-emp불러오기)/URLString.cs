using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace WindowsFormsApplication1
{
    static class URLString
    {
        private static String ip = "http://192.168.43.24";
        public static String readinfo = ip+"/Read.php?Str=SELECT * FROM info";
        public static String readmac = ip+"/Read.php ? Str=SELECT * FROM mac";

            
    }
}
