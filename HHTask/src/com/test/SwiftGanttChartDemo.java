package com.test;

import java.awt.Color;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;






public class SwiftGanttChartDemo {
	public static void main(String[] args) throws IOException{
		 //1. 首先初始化一个甘特图的主类com.yuxingwang.gantt.GanttChart,
		  //是一个继承自javax.swing.JScrollPanel的Swing的组件，通过它就可以用来显示甘特图：
//		  GanttChart  gantt = new GanttChart();
//
//		  //2. 设置甘特图的时间刻度的单位，如以星期为单位，则时间轴上的每一刻度代表一个星期： 
//		  gantt.setTimeUnit(TimeUnit.Day);
//
//		  //3. 配置甘特图,在这里您可以通过Config类配置您的甘特图，
//		  //您可以设置甘特图各个元素的颜色，宽度等，详细配置项请参考API文档中的Config类的说明。
//		  //Config config = gantt.getConfig();
//		  //config.setWorkingTimeBackColor(Color.red);
//		  Config config = gantt.getConfig();
//		  config.setWorkingTimeBackColor(Color.YELLOW);//设置工作时间背景色
//		  config.setTimeUnitWidth(50);//设置时间单位的显示宽度
//		  config.setWorkingDaysSpanOfWeek(new int[]{Calendar.MONDAY, Calendar.THURSDAY});//设置每周工作  
//		  //4. 创建甘特图的数据模型GanttModel，
//		  //所有的您要显示的项目任务信息都通过GanttModel来提供给GanttChart对象。      
//		 GanttModel  model = new GanttModel();
//
//		  //5. 设置项目开始时间和结束时间：
//		  model.setKickoffTime(new GregorianCalendar(2007, 1, 4));
//		  model.setDeadline(new GregorianCalendar(2007, 1, 15));
//
//		  //6. 创建甘特图的基本元素：任务对象Task。一个Task对象在甘特图中表现为一条横线。
//		  //每个Task对象都可以包含任意多的子Task对象，形成树状的任务模型。如果一个Task对象包含子Task,
//		  //则自动成为对象组，对象组仍然是Task对象，但是在甘特图中显示为不同的形状。
//		  //如下例，taskGroup就是任务组，包含了两个子任务，task1和task2：
//		  Task taskGroup = new Task("My Work 1", new GregorianCalendar(2007, 1, 4), new GregorianCalendar(2007, 1, 15));
//		  Task task1 = new Task("Sub-task 1", new GregorianCalendar(2007, 1, 4), new GregorianCalendar(2007, 1, 8));
//		  Task task2 = new Task("Sub-task 2", new GregorianCalendar(2007, 1, 8), new GregorianCalendar(2007, 1, 15));
//
//		  task1.setBackcolor(Color.CYAN);
//		  task1.setBackcolor(Color.green);
//		  taskGroup.add(new Task[]{task1, task2});
//
//		  //7. 指定任务之间的依赖关系。如果一项任务需要在另一项任务完成之后才能开始，
//		  //那么需要将另一项任务设为此任务的前置任务。：
//		  task2.addPredecessor(task1);
//
//		  //8. 将主任务组加入甘特图的Model中并将model对象传递给GanttChart：
//		  model.addTask(taskGroup);
//		  gantt.setModel(model);  
//		  //这时GanttChart就可以显示出甘特图来了。
//		  
//		  //指定路径，生成图片：
//		  String filePath = "D:\\gantt.jpg";
//		  gantt.generateImageFile(filePath);
	}
}
