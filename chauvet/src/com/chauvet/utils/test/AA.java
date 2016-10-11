package com.chauvet.utils.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.AbstractCellEditor;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;


/***
 * 维护表格
 * Utils.cleanOrderImage();
 * @author Administrator
 *
 */
public class AA extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel topPal;  // 顶部panel
	JPanel centerPal; //中间panel
	JPanel bottomPal; // 底部panel
    JButton addBtn; // 添加按钮（该按钮暂时没用到）
    JButton updateBtn; // 修改按钮（该按钮暂时没用到）
//    JButton closeBtn; //关闭按钮
    JButton okBtn; // 确定按钮
//    JButton cancleBtn; //取消按钮
    JTable table; // 菜品表格
    JComboBox comboBox; //桌号
    String selectedTableName = ""; //选择的桌号
    int selectTableIndex = 0; // 选择的桌号下标，用于点击确定的时候从缓存中清除
    
    JButton jiaBtn = new JButton(); // 每个菜品后面的+按钮
	JButton jianBtn = new JButton(); //每个菜品后面的-按钮
	JButton delsBtn = new JButton();//每个菜品后面的删除按钮
	
	ExtendedTableModel model = null; //表格的模式
	
	String selectRowValue = ""; //修改之前的值
	JPanel tabPal;  // 表格的panel
    
    public static void main(String[] args) {
    	String[] columnTitle = {"名称","类名称","操作"};
    	Object[][] tableData = {
            new Object[]{"发松岛枫是范德萨范德萨发斯蒂芬山东分公司的发生的","1",null},
            new Object[]{"b","2",null},
            new Object[]{"c","3",null},
            new Object[]{"d","4",null},
            new Object[]{"e","5",null},
            new Object[]{"f","6",null},
            new Object[]{"g","7",null},
            new Object[]{"h","8",null}
        }; 
        AA jTableDefaultTableModelTest = new AA(columnTitle,tableData);
        jTableDefaultTableModelTest.setVisible(true);
    }
    
    public AA(String[] columnTitle,Object[][] tableData)
    {
    	super("红板凳菜单扫描");
        topPal = new JPanel();
        centerPal = new JPanel();
        bottomPal = new JPanel();
        
        model = new ExtendedTableModel(columnTitle,tableData){
			private static final long serialVersionUID = 1L;
			@Override  
             public boolean isCellEditable(int row,int column){  
				if(column == 0){ // 菜品名称不可编辑
        			 return false;  
        		 }
        		 return true;
             }
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if(column == 1){
					try {
		                int m=Integer.parseInt((String) aValue);
		                if (m>999||m<0) {
		                	JOptionPane.showMessageDialog(null, "只能输入大于1小于999的数字！", "提示",JOptionPane.WARNING_MESSAGE);
		                    return;
		                }
		            } catch (NumberFormatException e) {
		            	JOptionPane.showMessageDialog(null, "只能输入大于1小于999的数字！", "提示",JOptionPane.WARNING_MESSAGE);
		                return;
		            }
				}
				super.setValueAt(aValue, row, column);
			}
        };
        
        table = new JTable(model);
        table.setRowSelectionAllowed(true);
        table.setRowHeight(36);     
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setColumnSelectionAllowed(true);   //可按列选择  
        table.setRowSelectionAllowed(true);  //可按行选择  
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object ob = model.getValueAt(selectedRow, 1);
                selectRowValue = ob.toString();
            }
        });
        
        /**监听关闭按钮，提示*/
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	int r = JOptionPane.showConfirmDialog(null,"关闭后将全部清空菜品,是否确认关闭？", "确认关闭", JOptionPane.YES_NO_OPTION,  
    		    		JOptionPane.QUESTION_MESSAGE); 
    		    if (r != JOptionPane.YES_OPTION) {  
    		    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
    		    	return;
    		    }
    		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    		    Utils.cleanOrderImage();//图片清空
            }
          });
        
        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setMaxWidth(100);
		
        ActionPanelEditorRenderer render = new ActionPanelEditorRenderer();
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column2.setCellRenderer(render);		
		ActionPanelEditorEditor editor = new ActionPanelEditorEditor();
		column2.setCellEditor(editor);		
        
        JScrollPane jScrollPane = new JScrollPane(table);
        centerPal.add(jScrollPane,BorderLayout.CENTER);
        
        
        JLabel label=new JLabel("桌号:");
        topPal.add(label);
//        ArrayList<String[]> arrayList = new ArrayList<String[]>();
//        arrayList.add(new String[]{"0","---请选择---"});
//        arrayList.add(new String[]{"ID1","大厅1号桌"});
//        arrayList.add(new String[]{"ID2","大厅2号桌"});
//        arrayList.add(new String[]{"ID3","大厅3号桌"});
        
        ArrayList<String[]> arrayList = new ArrayList<String[]>();// TableCache.getInstances();
        if(arrayList.size() <= 0){
        	arrayList.add(new String[]{"0","---请选择---"});
        }
        final ArrayListComboBoxModel listModel = new ArrayListComboBoxModel(arrayList);
        comboBox = new JComboBox(listModel);
        comboBox.setSelectedIndex(0);
        /**下拉框事件*/
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (e.getSource() == comboBox) {
            		   int index = comboBox.getSelectedIndex(); //获取下标
            		   selectTableIndex = index;
            		   listModel.setSelectIndex(index);
            		   String tableMessage = listModel.toString();
            		   selectedTableName = tableMessage;
            	}
            }
        });
        topPal.add(comboBox);  
        
        
        okBtn = new JButton("确定");
		okBtn.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  if(selectTableIndex == 0){
				  JOptionPane.showMessageDialog(null, "请选择桌号！", "提示",JOptionPane.WARNING_MESSAGE);
				  return;
			  }
			  
//			  Utils.cleanOrderImage();
			  TableModel tm = table.getModel();
			  int rowCount = tm.getRowCount();
			  String[][] foods = new String[rowCount][3];
			  for (int i = 0; i < rowCount; i++) {
				  System.out.println(table.getValueAt(i, 0)+"--"+table.getValueAt(i, 1));
				  
				  String foodName = (String) table.getValueAt(i, 0);
				  String foodCount = (String) table.getValueAt(i, 1);
				  String[] strs = new String[]{foodName,foodCount,null};
				  foods[i] = strs;
				  
			  }
			  
			  System.out.println("selectedTableIndex=="+selectedTableName);
//			  TableCache.remove(selectTableIndex); // 缓存中移除该桌号
			  Properties p = new Properties();
			 /* try {
				  InputStream inputStream = new FileInputStream(InterfaceConfig.getConfig()); // 获取配置文件
	              p.load(inputStream);//从文件里中加载配置文件
			  } catch (IOException e1) {   
				  e1.printStackTrace();   
			  }*/
	          /*String interAddr = p.getProperty("http.url");
	          Map<String,String> parMess = getShopIdByUrl(interAddr);
	          String shopId = parMess.get("shop_id");
		      System.out.println("shopId==="+shopId);
			  
			  String path = "D:/121/1.jpg";
	    	   int heigth = CreateImage.graphicsGeneration(foods,path);

	    	   	MenuVo menuVo =new MenuVo();
		   		menuVo.setId("1");
		   		menuVo.setIp("xp-80c");
		   		menuVo.setPicWidth("800");
		   		menuVo.setPicHeight(String.valueOf(heigth));
		   		menuVo.setPicPath("file:////"+path);
		   		menuVo.setPrintTime("2016-09-13 10:48:50");
		   		menuVo.setPrintType("1");*/
	   		
		   		try {
//					PicPrintService.getInstance().print(menuVo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			  dispose();
		  }
		});
		bottomPal.add(okBtn);
        
        
        
		JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    model.removeRow(selectedRow);  //删除行
                }
            }
        });
        bottomPal.add(delButton);
        
		this.setBounds(100, 100, 500, 550);
		this.setLayout(new BorderLayout());
		this.add(topPal,BorderLayout.NORTH);
		this.add(centerPal,BorderLayout.CENTER);
		this.add(bottomPal,BorderLayout.SOUTH);        
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		this.setLocationRelativeTo(null);
		
    }
    
    /***
     * 将url中的参数转换为map
     * 				
     * @param url
     * 				
     * @return
     * 			Map<String,String>
     */
    public static Map<String,String> getShopIdByUrl(String url){
    	 //http://www.hbdworld.com.cn/v_3_app/Print/printOrderNet?shop_id=12&token=1
    	String tempUrl = "";
    	Map<String,String> urlParMap = new HashMap<String,String>();
    	if(StringUtils.isNotBlank(url)){
    		tempUrl = url.substring(url.indexOf("?")+1, url.length());
        	String[] strs = tempUrl.split("&");
        	for (String str : strs) {
        		String[] keyAndVal = str.split("=");
        		urlParMap.put(keyAndVal[0], keyAndVal[1]);
    		}        	
    	}
    	return urlParMap;
    }
    
   /* *//***
     * table监听
     *//*
    boolean flag = false;
    @Override
    public void tableChanged(TableModelEvent e) {
    	if(e.getType() == -1){ //删除不验证
    		return;
    	}
    	if(!flag){
    		flag = true;
    		String strVal = "";
    		int row = e.getFirstRow();
    		TableModel tm = (TableModel)e.getSource();  
    		strVal = (String)tm.getValueAt(row, 1);
    		try {
    			int val = Integer.parseInt(strVal);
    			if(val <= 0){
    				JOptionPane.showMessageDialog(this, "不能小于1！", "提示",JOptionPane.WARNING_MESSAGE);
    				val = 1;
    			}
    			if(val >= 999){
    				JOptionPane.showMessageDialog(this, "不能超过999！", "提示",JOptionPane.WARNING_MESSAGE);
    				val = 999;
    			}
    			table.getModel().setValueAt(""+val, row, 1);
    		} catch (Exception e2) {
    			table.getModel().setValueAt(selectRowValue, row, 1);
    			JOptionPane.showMessageDialog(this, "只能输入大于1小于999的数字！", "提示",JOptionPane.WARNING_MESSAGE);
    		}
    	}
    	flag = false; 
    }
    */
    
    /** -------------------------------   **/
    class ExtendedTableModel extends DefaultTableModel{
    	 
    	private static final long serialVersionUID = 1L;

    	public ExtendedTableModel(String[] columnTitle, Object[][] tableData) {
            super(tableData,columnTitle);
        }
        
        @Override
        public Class<? extends Object> getColumnClass(int columnIndex) {//重写getColumnClass方法,根据每列的第一个值返回该列真实的数据类型
            return getValueAt(0, columnIndex).getClass();
        }
    }
    
    class ActionPanelEditorRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public ActionPanelEditorRenderer() {
			setOpaque(true);
			init();
		}

		private void init() {
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			add(new JButton("-"));
			add(new JButton("+"));
			add(new JButton("删除"));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			return this;
		}
    }
    
    class ActionPanelEditorEditor  extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		JPanel panel2 = new JPanel();  
    	
    	public ActionPanelEditorEditor() {
    		super();    	
    		jianBtn.setText("-");
    		panel2.add(jianBtn);    		
    		jiaBtn.setText("+");
    		panel2.add(jiaBtn);
    		delsBtn.setText("删除");
    		panel2.add(delsBtn);
    		
    		/** 递增 */
    		jiaBtn.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				int i = table.getSelectedRow();
    				String s = (String)model.getValueAt(i, 1);
    				int tempVal = (Integer.parseInt(s) + 1);
    				if(tempVal >= 999){
    					tempVal = 999;
    				}
    				model.setValueAt(""+tempVal, i, 1);
    			}
    		});
    		
    		/** 递减 */
    		jianBtn.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				int i = table.getSelectedRow();
    				String s = (String)model.getValueAt(i, 1);
    				int tempVal = (Integer.parseInt(s) - 1);
    				if(tempVal <= 1){
    					tempVal = 1;
    				}
    				model.setValueAt(""+tempVal, i, 1);
    			}
    		});
    		
    		/** 删除 */
    		delsBtn.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				int rowcount = model.getRowCount()-1;//getRowCount返回行数，rowcount<0代表已经没有任何行了。
                    int selectedRow = table.getSelectedRow();//获得选中行的索引
                    if(rowcount >= 0){  //存在选中行
                        model.removeRow(selectedRow);  //删除行
                        model.setRowCount(rowcount);
                    } 
                    table.revalidate();
    			}
    		});
    	}
		@Override
		public Object getCellEditorValue() {
			return null;
		}

    	@Override
    	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    		panel2.setBackground(table.getSelectionBackground());
    		return panel2;
    	}
    	
    }
    
}


@SuppressWarnings({ "rawtypes", "serial" })
class ArrayListComboBoxModel extends AbstractListModel implements ComboBoxModel {
	private Object selectedItem;
	
	private int selectIndex;
	
	public int getSelectIndex() {
		return selectIndex;
	}
	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}

	private ArrayList<String[]> anArrayList;
	
	public ArrayListComboBoxModel(ArrayList<String[]> arrayList) {
		anArrayList = arrayList;
	}
	public Object getSelectedItem() {
		return selectedItem;
	}
	
	public void setSelectedItem(Object newValue) {
		selectedItem = newValue;
	}
	
	public int getSize() {
		return anArrayList.size();
	}
	public Object getElementAt(int index) {
		return anArrayList.get(index)[1];
	}
	
	@Override
	public String toString() {
		selectIndex = this.getSelectIndex();
		String[] strs = anArrayList.get(selectIndex);
		return strs[0]+"="+strs[1];
	}
}














