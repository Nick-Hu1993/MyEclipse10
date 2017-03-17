package org.test;

public class myDog {
	public static void main(String[] args) {
		//声明dog数组，为其分配空间
		dog1[] d = new dog1[3];
		//创建dog对象
		d[0] = new dog1();
		d[1] = new dog1();
		d[2] = new dog1();
		//给每只dog取名字
		d[0].name = "马晓荣";
		d[1].name = "钱肖";
		d[2].name = "赵耿坚";
		//给每只dog称体重
		d[0].height = 60;
		d[1].height = 70;
		d[2].height = 15;
		//调用dark方法判断dog的体型
		d[0].dark();
		d[1].dark();
		d[2].dark();
	}
}

//声明dog这个类，以备用
class dog1{
	String name;
	int height;
	public void dark(){
	if(height >= 60){
			System.out.println("猪");		
		}else if (  height > 20 && height <= 59 ) {
			System.out.println("熊");
		}else if(height < 19){
			System.out.println("猴子");
		} else {
			System.out.println("请认真点输入，吊毛！");
		}
	}
}