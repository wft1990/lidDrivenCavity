package cn.edu.tju.main;


public class ParDefinition {
	/**
	 * 閻庤鐭粻鐔煎箥閿熺晫鎲版担鐑樻殢闁告帗澹嗗▓鎴﹀触閸曨収娼氶柛娆忓�閺嗭拷
	 */
	public static double dt; 			//闁哄啫鐖煎Λ鍨潰閵夆晜姣�
	public static int tfrom,tend;		//闁告帗绻傞～鎰板籍閸洘锛熼柕鍡曡兌缁劑寮堕悢鍛婎槯闂傚偊鎷�
	public static double dx = 0.02;		//婵炲柌鍕�缂傚啯鍨堕悧鎼佹倷瑜版帗锛熼悹鐚存嫹
	public static double dy = 0.02;
	public static double dxy = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	//public static int npml = 6;
	public static double rl = 0.0,rr = 1.0,rd = 0.0,rt = 1.0;	//閻犱緤绱曢悾濠氬春閻旀灚浜ｉ悘蹇撻獜缁憋拷10~10
	public static int im = (int)((rr-rl)/dx)+1;		//x闁哄倻鎳撻幃婊勭▔婵犲喚鍎岄柟宄邦槺缂嶅寮介懖鈺佷化闁汇劌瀚柌婊堝极閿燂拷
	public static int jm = (int)((rt-rd)/dy)+1;		//y闁哄倻鎳撻幃婊勭▔婵犲喚鍎岄柟宄邦槺缂嶅寮介懖鈺佷化闁汇劌瀚柌婊堝极閿燂拷
	//public static int nlp = 145;		//闁革箑妫欓悡鎾儍閸曨厼浠柡浣稿簻缁辨瑩宕￠搹顐㈩�闁哄秴鍚嬪﹢鏇㈠籍閵壯冧化闁挎冻鎷�
	//public static int rl = -10,rr = 10,rd = -10,rt = 10;	//閻犱緤绱曢悾濠氬春閻旀灚浜ｉ悘蹇撻獜缁憋拷10~10
	
	
	//闁哄倽顫夐悡鎾锤閹邦厾鍨奸柣鎰贡濞堟垶绌遍埄鍐х礀(s 濞寸媴缍�妴鍍簈uare)
	public static double sl = -6.0, sr = -4.0, sd = -1.0, st= 1.0;// 閻庤鐭粻鐔煎棘鐟欏嫮鍙撮柣銊ュ缂嶅懐绱旈鐓庢尋闂傦拷鐏濋鏃傜驳婢跺绻嗛柟顓у灲缁辨繈寮憴鍕彺闁哄嫷鍨电粩鐔兼⒐婢舵瓕绀�闁汇劌瀚婊堝棘閻熸媽鍩�
	public static int sim = (int)((sr-sl)/dx)+1;//闁哄倽顫夐悡鎾儍閸曨厼浠柡渚婃嫹
	public static int sjm = (int)((st-sd)/dy)+1;
	//public static double[] sx = new double[sim]; //閻庢稒蓱閺備線寮憴鍕彺闁告艾瀚粩鐔兼儍閸曨偅缍忛柡宥忔嫹
	//public static double[] sy = new double[sjm]; 
	

	
	public static double[][] x = new double[im][jm];  	//閻庢稒蓱閺備礁鈻庤婵椽鎮欓悷鐗堢稄闁哄稄鎷�
	public static double[][] y = new double[im][jm];  
	public static double[][] u = new double[im-1][jm];	//閻庢稒蓱閺備礁鈻庤婵椽鎮欓崷顓熺暠u闁靛棔绠嶉柕鍡曞灑
	public static double[][] v = new double[im][jm-1];
	public static double[][] us = new double[im-1][jm];	//閻庢稒蓱閺備礁鈻庤婵椽鎮欓崷顓熺暠u闁靛棔绠嶉柕鍡曞灑
	public static double[][] vs = new double[im][jm-1];
	public static double[][] ud = new double[im-1][jm];	//閻庢稒蓱閺備礁鈻庤婵椽鎮欓崷顓熺暠u闁靛棔绠嶉柕鍡曞灑
	public static double[][] vd = new double[im][jm-1];
	public static double[][] p = new double[im][jm];
	public static double[][] ps = new double[im][jm];
	public static double[][] pp = new double[im][jm];
	public static int[][] just = new int[im][jm];		//闁哄秴娲╅鍥╃磾閹寸偟澹愰柣鎰潐濡叉悂宕ラ敃锟借含闁哄倽顫夐悡瀛樻媴閹炬剚妯嗛柨娑樿嫰濠�亝鎷呴幘鎰佹▎濞戞搫鎷�闁挎稑鑻﹢顏呮媴閹惧箍锟介梻鍫涘灩瀵攱鎷呴幘鍐叉暥濞戞搫鎷�
	
	

	public static double c_air = 1, rho_air = 1,alpha = 1;
	public static double flow_resistance = alpha * Math.pow(rho_air, 2) * Math.pow(c_air, 2); 
	public static double kappa = 1.0 / (rho_air * Math.pow(c_air, 2));
	
	
}
