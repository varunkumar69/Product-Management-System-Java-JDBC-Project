import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Product extends Admin{
	int product_id;
        String url="jdbc:mysql://localhost:3306/javaproject";
        String uname="root";
        String pass="123";
	
		
	void productList(){
		
				//Product obj6=new Product();
				
				System.out.println("****Our Products******");
				System.out.println("1.Ion\n2.Mastercraft\n3.Digitate\n4.Edumate\n");
				int choice=vn.nextInt();
				product_id=choice;
				//here set the choice as product_id
				switch(choice){
				
					case 1:
						//obj6.
                                                ion();
						//obj6.productMenu();
                                                productMenu();
						break;
						
					case 2:
						//obj6.mastercraft();
                                                mastercraft();
						//obj6.productMenu();
                                                productMenu();
						break;
						
					case 3:
						//obj6.digitate();
                                                    digitate();
						//obj6.productMenu();
                                                    productMenu();
						break;
						
					case 4:
						//obj6.edumate();
                                                edumate();
						//obj6.productMenu();
                                                productMenu();
						break;
						
					default:
						System.out.println("Invalid input");
						break;
				}
	}
	void ion(){
		
		System.out.println("Welcome to ion,Write contents about Ion");
		
	}

	void mastercraft(){
		
		System.out.println("Welcome to Mastercraft,Write contents about MasterCarft");

	}

	void digitate(){
		
		System.out.println("Welcome to Digitate,Write contents about Digitate");


	}

	void edumate(){
		
		System.out.println("Welcome to Edumate,Write contents about Edumate");

	}
	
	void productMenu(){
		
	//Product obj14=new Product();   
        int flag=0;
        do{
	System.out.println("************Client Portal*************"); 
	System.out.println("1.Purchase\n2.Tariff\n3.Product Analysis\n4.Add to Cart\n");
	int choice=vn.nextInt();
	   
	switch(choice){
			

		case 1:
                    //obj14.purchase();
                    purchase();
                    break;
				
		case 2:
                     System.out.println("Enter product id of the product ");
                      product_id=vn.nextInt();
                    
                    
                             try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        String query="select price from productdetails where product_id= "+ product_id +" ";
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println("Tariff of the Product is  "+rs.getString("price")+" Rs");
        
        
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                    
 			
                    break;
		case 3:
                    
                    
                    //obj14.productAnalysis();
                    productAnalysis();
                    break;
                    
                    
		case 4: 
                            
            System.out.println("Enter Client id");
            int client_id=vn.nextInt();
            System.out.println("enter product_id");
            product_id=vn.nextInt();
                 
        //adding product to cart         
        try{
            
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        String query="select product_name from productdetails where product_id="+product_id+"";
        ResultSet rs=st.executeQuery(query);
         
        rs.next();
        
        
        String pname=rs.getString("product_name");
        
        String query1="insert into cart(client_id,product_id,product_name)"+"values(?,?,?)";
        //for table sales
        PreparedStatement nm=con.prepareStatement(query1);
        
        nm.setInt(1,client_id);
        nm.setInt(2,product_id);
        nm.setString(3,pname);
        
        int count=nm.executeUpdate();
        
        System.out.println(count +"row affected in table cart");
        System.out.println(pname+" has been added to your cart");
       
        
        nm.close();
        
        con.close();
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                        
					break;
					
					
			default:
					System.out.println("Invalid input");
					break;

		}
                System.out.println("***Product Menu***\nPress 1 to continue\n0 to exit\n");
                flag=vn.nextInt();
        }while(flag==1);
	

	}
	
	void purchase(){
		
		int year;
		int cvv;
		int choiceVariable;
		Long card_number;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("**********Purchase Portal***********");
                
                System.out.println("Enter client_id");
                int client_id=vn.nextInt();
		
                System.out.println("Enter product_id");
                 product_id=vn.nextInt();
                
		System.out.println("Enter term for purchase");
		year=sc.nextInt();
		
		System.out.println("*****CARD DETAILS*****");
		
		System.out.print("Card number: \t");
		card_number=sc.nextLong();
		
		System.out.print("CVV:  \t");
		cvv=sc.nextInt();
		
		System.out.println("Do you want to make the transaction?");
		System.out.println("Press 1 for yes\nPress 2 for no");
		choiceVariable=sc.nextInt();
		
	switch(choiceVariable)
	{
	case 1:
            
        try{
            
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        String query="select product_name,price from productdetails where product_id="+product_id+"";
        ResultSet rs=st.executeQuery(query);
         
        rs.next();
        
        
        String name=rs.getString("product_name");
        int price=rs.getInt("price");
        
        
        String query1="insert into sales(product_id,product_name,price)"+"values(?,?,?)";
        String query2="insert into orders(client_id,product_id,product_name,date)"+ "values(?,?,?,?)";
        //for table sales
        PreparedStatement nm=con.prepareStatement(query1);
        
        nm.setInt(1,product_id);
        nm.setString(2,name);
        nm.setInt(3,price);
        
        int count=nm.executeUpdate();
        
        System.out.println(count +"row affected in table sales");
        
        nm=con.prepareStatement(query2);
        nm.setInt(1,client_id);
        nm.setInt(2,product_id);
        nm.setString(3,name);
        nm.setString(4,"2020-03-24");
        
        int count1=nm.executeUpdate();
        
        System.out.println(count1 +"row affected in table orders");
        
        nm.close();
        
        con.close();
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }    
        System.out.println("Transaction completed");                            
                            
                            
                            
                            
                                        
						
					
			break;
			case 2:
					System.out.println("Transaction failed");
					break;

			default:
					System.out.println("*****Invalid input*****");
					
		}



	}
	void productAnalysis(){

		System.out.println("****************Product Analysis*****************");
                
                
                
                
                System.out.println("Enter product id of the product ");
                 product_id=vn.nextInt();
        
        
        String query="select price from productdetails where product_id= "+ product_id +" ";
        String query1="select count(product_id) from  sales where product_id="+product_id+"";
         
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println("Price of the Product is"+rs.getString("price"));
        
        rs=st.executeQuery(query1);
        rs.next();
        System.out.println("Product sales :"+rs.getInt("count(product_id)"));
        
        
        con.close();
        st.close();
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                
        
        }
 
		
		
}


class Client extends Product{
	
	String client_username;
	String client_password;
	String name;
	String address;
	String email_address;
	Long contact_number;
	int client_id;
        
	
	void clientMenu(){
		
			//Client obj3=new Client();
			
			System.out.println("**************Client Login****************");
			System.out.println("1.Login\n2.New User\n");
			int choice=vn.nextInt();
			
			switch(choice){
				
					case 1:
							//obj3.loginClient();
                                                        loginClient();
							break;
					case 2:
							//obj3.addNewClient();
                                                        addNewClient();
							break;
						
					default:
							System.out.println("Invalid input");
			}
	}
	
	void loginClient(){
			//Client obj11=new Client();
			int flag=0;
                         String usernameVariable=null;
                         String passwordVariable=null;
			System.out.println("****************Login Portal***********\n");
			System.out.println("Enter client id ");
			client_id=vn.nextInt();//primary
                        
                        
                        
                        
                        
                        try{
        String query="select username,password from clientdetails where client_id="+client_id+"";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        
        rs.next();
         usernameVariable=rs.getString("username");
         passwordVariable=rs.getString("password");
        
        
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                               
                        
                         	
			do{
				flag=0;
			System.out.println("enter username");
			client_username=vn.next();
			
			if(!(usernameVariable).equals(client_username)){
				
				System.out.println("\nIncorrect Username\n Please enter Valid Username\n");
				flag=1;
				
			}
	
			}while(flag==1);
			
				flag=0;
                        
                        
			do{
			System.out.println("enter password");
			client_password=vn.next();
				
		if(client_username.equals(usernameVariable) && client_password.equals(passwordVariable)){
			//here set the client_id
			//obj11.afterLoginClientMenu();
                        afterLoginClientMenu();
                        flag=1;
		}
		else{
		
			System.out.println("Invalid username and password\nPlease enter correct username and password\n");
		}
                        }while(flag!=1);
	}
	
	
	void afterLoginClientMenu(){
                                        System.out.println("Press 1 to continue");
					Client obj12=new Client();
					int flag=0;
                                        
                                        do{
					System.out.println("************** Client Menu ****************");
					System.out.println("\n1.Product List\n2.To view your cart\n3.My Orders\n4.Edit Details\n5.feedback\n");
					int choice =vn.nextInt();
					
					switch(choice)
					{
						case 1:	
								obj12.productList();
								break;
						case 2:
								obj12.cart();
								break;
						case 3:
								obj12.myOrder();
								break;
						case 4:
								obj12.editClientDetails();
								break;
								
						case 5:
								obj12.feedback();
								break;
						default:
						
								System.out.println("invalid input");
								break;
					}
                                        System.out.println("***Client Menu***\nPress 1 to continue\n0 ro exit\n");
                                        flag=vn.nextInt();
                                        }while(flag==1);
	}
                                        
                                        
                                        
	void cart(){
		
		System.out.println("***********Cart*********\n");
		
               
                System.out.println("Enter client id of the product ");
                 client_id=vn.nextInt();
       
         
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
         String query="select product_name from cart where client_id= "+ client_id +" ";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        System.out.println("Cart details : \n");
        while(rs.next())
        {
        
        System.out.println(rs.getString("product_name"));
        
        }
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                
              
	}

	void myOrder(){
		System.out.println("**********Orders**********");
		
		System.out.println("Enter Client id");
		 client_id=vn.nextInt();
		
		System.out.println("Your previous orders are");
                
                
                
                 try{
        String query="select product_name,date from orders where client_id= "+ client_id +" ";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        
        while(rs.next())
        {
        
        System.out.println(rs.getString("product_name")+"  purchased on  "+rs.getString("date"));
        
        }
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }    	
	}
        
	void feedback(){
            System.out.println("***********Feedback**********");
            System.out.println("Enter  client id");
             client_id=vn.nextInt();
             vn.nextLine();
				
            System.out.println("Enter your feedback here");
            String feedbackVariable=vn.nextLine();
                                
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
       
        String query1="insert into feedback(client_id,feedback)"+"values(?,?)";             
       
        //for table sales
        PreparedStatement nm=con.prepareStatement(query1);
        nm.setInt(1,client_id);
        nm.setString(2,feedbackVariable);
       
        
        int count=nm.executeUpdate();
        System.out.println(count +"row's affected in table feedback");
        
        nm.close();
        
        con.close();
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                                
				
	System.out.println("Thank You for your valuable feedback,our team will reach you soon");
				
	}
	
	void editClientDetails(){
		
		System.out.println("*************Edit Details***********");
		
		System.out.println("Entter Client  id");
		int id=vn.nextInt();
		System.out.println("***Please Select which data you want to Edit***\n");
		System.out.println("1.username\n2.password\n3.name\n4.contact_number\n5.email_address\n");
		int choiceVariable=vn.nextInt();
                
		
                 try{
            Scanner vn=new Scanner(System.in);
        
        String query="update clientdetails set username=?  where client_id=?";
        
        String query1="update clientdetails set password=? where client_id=?";
        String query2="update clientdetails  set name=? where client_id=?";
        String query3="update clientdetails set contact_no=? where client_id=?";
        String query4="update clientdetails set email_id=? where client_id=?";
        
         Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        
        PreparedStatement st=con.prepareStatement(query);
                
                
		switch(choiceVariable)
		{
			case 1:
					System.out.println("Enter the  new username ");
					String new_username=vn.next();
                                        st.setString(1,new_username);
                                        st.setInt(2,id);
                                        st.executeUpdate();
                                        System.out.println("username updated");
					
					break;
			case 2:
					System.out.println("Enter the  new password ");
					String new_password =vn.next();
                                        st=con.prepareStatement(query1);
                                        st.setString(1,new_password);
                                        st.setInt(2,id);
                                        st.executeUpdate();
                                        System.out.println("password updated");
					
					break;
			case 3:
					System.out.println("Enter the  new name ");
					String new_name=vn.next();
                                        st=con.prepareStatement(query2);
                                        st.setString(1,new_name);
                                        st.setInt(2,id);
                                        st.executeUpdate();
                                        System.out.println("name updated");
                                        
					break;
			case 4:
					System.out.println("Enter the  new contact no");
					Long new_contact_no=vn.nextLong();
                                        st=con.prepareStatement(query3);
                                        st.setLong(1,new_contact_no);
                                        st.setInt(2,id);
                                        st.executeUpdate();
                                        System.out.println("contact no updated");
                                        
					break;
			case 5:
					System.out.println("Enter the  new email_address ");
					String new_email_id=vn.next();
                                        st=con.prepareStatement(query4);
                                        st.setString(1,new_email_id);
                                        st.setInt(2,id);
                                        st.executeUpdate();
                                        System.out.println("email_id updated");
					break;
			
			default:
					System.out.println("Invalid Input");
					
					
		}
                con.close();
                st.close();
                }
                catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        } 
                 

        }


	void addNewClient(){
		
		System.out.println("*********New Registration******");

                
                System.out.println("create client_id");
                 client_id=vn.nextInt();
                 
                 
                vn.nextLine();
		

		System.out.println("create a username\n");
		client_username=vn.next();
                if((client_username.length())==0){
			
			throw new InputMismatchException(" username cannot be blank");
			
		}
                

		System.out.println("create a password\n");
		client_password=vn.next();
                if((client_password.length())==0){
			
			throw new InputMismatchException(" password cannot be blank");
			
		}
		
		System.out.println("enter name");
		name=vn.next();
                if((name.length())==0){
			
			throw new InputMismatchException(" name cannot be blank");
			
		}
                int k=0;

                if (name != null && !name.isEmpty()) {
                    for (char c : name.toCharArray()) {
                        if (Character.isDigit(c)) {
                                 k++;
                        }
                    }
                }
                if(k>0){
		
                    throw new InputMismatchException("Name should not contain digits");
                }
                
                	
                String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
                String str2[]=name.split("");
                for (int i=0;i<str2.length;i++){
    
                    if (specialCharacters.contains(str2[i])){
                        throw new InputMismatchException(" name cannot have special characters");
                    }
                }
                
                vn.nextLine();
		
		System.out.println("enter Address");
		address=vn.next();
                if((address.length())==0){
			
			throw new InputMismatchException(" address cannot be blank");
			
		}
                vn.nextLine();
		
		System.out.println("Enter Email Address");
		email_address=vn.next();
                if((email_address.length())==0){
			
			throw new InputMismatchException("  email address cannot be blank");
			
		}
                k=0;
		if(email_address.endsWith(".com")) {
		k++;
                }
                if(k==0){
			
			throw new InputMismatchException("Incorrect email id ---- It should end with .com ");
			
		}
                vn.nextLine();
		
		System.out.println("Enter Contact Number");
		contact_number=vn.nextLong();
                vn.nextLine();
                
                int length = String.valueOf(contact_number).length();
                
                if(length!=10){
                    throw new InputMismatchException("contact no should be of 10 digits");
                }
                
		
		

		
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
      
        String query1= "insert into clientdetails(client_id,username,password,name,address,email_id,contact_no)"+ "values(?,?,?,?,?,?,?)";             
       
        //for table sales
        PreparedStatement nm=con.prepareStatement(query1);
         
        nm.setInt(1,client_id);
         
        nm.setString(2,client_username);
        nm.setString(3,client_password);
        nm.setString(4,name);
        nm.setString(5,address);
        nm.setString(6,email_address);
        nm.setLong(7,contact_number);
         
        
        int count=nm.executeUpdate();
        System.out.println("done");
        System.out.println(count +"row's affected in table client details");
        System.out.println("Your registration was successful\n");
        System.out.println("done");
        nm.close();
        
        con.close();
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql exception");
        }
        
           
                
 
	}

}



class Admin {
	
	Scanner vn=new Scanner(System.in);
	String admin_username;
	String admin_password;
	int admin_id;
	int password=vn.nextInt();
        String url="jdbc:mysql://localhost:3306/javaproject";
        String uname="root";
        String pass="123";
	
	
	void afterLoginAdminMenu(){
			
			//Admin obj7=new Admin();
                        System.out.println("\nPress 1 to continue\n");
			UpdateDetails obj8=new UpdateDetails();
			
			int flag=0;
                        do{
                            
                        System.out.println("\n\n***********Admin Portal***********\n");
                        System.out.println("Press 1 to view Admin Menu\n");
			
                        
                        ProductDevelopment obj9=new ProductDevelopment();
			System.out.println("\n***Admin Menu***\n1.View client details \n2.View Product details \n3.Delete Client\n4.Sales\n5.Revenue\n6.Add New Project\n7.Get New Product Details.\n");
			int choice=vn.nextInt();
			
 			switch(choice){
			
				case 1:
					obj8.getClientData();
					break;
				case 2:
					obj8.getProductData();
					break;
				case 3:
					obj8.deleteClient();	
					
					break;
				case 4:
					//obj7.sales();
					sales();
					break;
				
				case 5:
					//obj7.revenue();
					revenue();
					break;
				case 6:
					obj9.addProject();
					break;
				case 7:
					obj9.getNewProductDetail();
					break;
					
				
				default:
					System.out.println("Invalid Input");
					break;
			} 
                        System.out.println("\n\n***Admin Menu****\nPress 1 to continue \nPress 0 to exit\n");
                        flag=vn.nextInt();
                        }while(flag==1);

	}
	
	void aboutUs(){
		
		System.out.println("*********About Us**********");

		System.out.println("Welcome to TCS");

	}
	
	void revenue(){
					System.out.println("***********Revenue********");
					System.out.println("1.Total revenue\n2.Product wise revenue");
					int choiceVariable=vn.nextInt();
					
					switch(choiceVariable){
						
					case 1:     try{
        String query="select sum(price) from sales ";                                  
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println("****Revenue****");
        System.out.println("Total revenue   :"+rs.getInt("sum(price)")+" Rs");
        con.close();
        st.close();
        }
         catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
    
                                        break;
							
					case 2:
							System.out.println("Enter  product id");
							int id=vn.nextInt();
							
                                                                                                        
        try{
                                                           
        String query1="select product_name,sum(price) from sales where product_id="+id+" ";                                                
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query1);
        rs.next();
        System.out.println("Total revenue of "+rs.getString("product_name")+" is "+ rs.getInt("sum(price)")+" Rs");
        
        
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                                                      
							break;
							
					default:
					
							System.out.println("Invalid input");
							break;
					}

	}

	void sales(){
				System.out.println("********Sales**********");
				
                                try{
            
        String query="select product_name,count(product_id) from  sales where product_id='1'";
        String query1="select product_name,count(product_id) from  sales where product_id='2'";
        String query2="select product_name,count(product_id) from  sales where product_id='3'";
        String query3="select product_name,count(product_id) from  sales where product_id='4'";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println(rs.getString("product_name")+" : units sold "+rs.getInt("count(product_id)"));
        rs=st.executeQuery(query1);
        rs.next();
        System.out.println(rs.getString("product_name")+" : units sold "+rs.getInt("count(product_id)"));
        rs=st.executeQuery(query2);
        rs.next();
          System.out.println(rs.getString("product_name")+" : units sold "+rs.getInt("count(product_id)"));
        rs=st.executeQuery(query3);
        rs.next();
         System.out.println(rs.getString("product_name")+" : units sold "+rs.getInt("count(product_id)"));
        
        
       
        con.close();
        st.close();
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                                

	}


	void loginAdmin(){
		
			//Admin obj10=new Admin();
                        int flag=0;
			
			System.out.println("**********Admin Login***********");
			
			System.out.println("------enter username and password--------\n");
			
			
                        do{
				flag=0;
			System.out.println("enter username(abc)");
			admin_username=vn.next();
			
			if(!("abc").equals(admin_username)){
				
				System.out.println("\nIncorrect Username\n Please enter Valid Username\n");
				flag=1;
				
			}
			
			
			}while(flag==1);
			
				flag=0;
                        
                        
			
                        do{
			System.out.println("enter password(123)");
			admin_password=vn.next();
			
			if(admin_username.equals("abc") && admin_password.equals("123")){
				
				//obj10.afterLoginAdminMenu();
				afterLoginAdminMenu();
                                flag=1;
                                
				
			}
			else{
		
			System.out.println("Invalid username and password\nPlease enter correct username and password\n");
			
			}
                        }while(flag!=1);
	}

}




class UpdateDetails extends Admin{
    
    
        String url="jdbc:mysql://localhost:3306/javaproject";
        String uname="root";
        String pass="123";
        


	void getClientData(){
		
		System.out.println("***********Client Data retrieval************");

		System.out.println("Enter Client id");
                int client_id=vn.nextInt();
                
                
                 try{
        String query="select client_id,name,address,email_id,contact_no from clientdetails where client_id="+client_id+"";
        String query1="select product_name,date from orders where client_id= "+ client_id +" ";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        System.out.println("Client details : \n");
        rs.next();
        
        
        System.out.println("client_id :"+rs.getInt("client_id")+" \nname :  "+rs.getString("name")+"\n address :"+rs.getString("address")+"\n email_id :"+rs.getString("email_id")+"\n contact_no :"+rs.getLong("contact_no"));
        
        
        rs=st.executeQuery(query1);
        System.out.println("Client Orders");
        while(rs.next())
        {
        
        System.out.println(rs.getString("product_name")+"  purchased on  "+rs.getString("date"));
        
        }
        
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
    
	}

	void getProductData(){
		System.out.println("*************Product Data Retrieval************");
		
		System.out.println("Enter  Product id");
		int product_id=vn.nextInt();
                
                 try{
        String query="select price from productdetails where product_id= "+ product_id +" ";
        String query1="select count(product_id) from  sales where product_id="+product_id+"";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println("Product Details");
        
        System.out.println("Price of the Product is"+rs.getString("price"));
        rs=st.executeQuery(query1);
        rs.next();
        System.out.println("Product sales :"+rs.getInt("count(product_id)"));

        con.close();
        st.close();
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                
                
                
                
                

	}
	
	void deleteClient(){
		
		System.out.println("**********Deleletion of the Client from the database************");
		
		
		System.out.println("Enter Client id");
		int client_id=vn.nextInt();
                
                try{
            
        
        String query="delete from  clientdetails   where client_id=?";
        
        String query1="delete from  cart  where client_id=?";
        String query2="delete from orders   where client_id=?";
        String query3="delete from feedback  where client_id=?";
        
        
        
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        
        PreparedStatement st=con.prepareStatement(query);
        
        
        st.setInt(1,client_id);
        st.executeUpdate();
        
        st=con.prepareStatement(query1);
        
        st.setInt(1,client_id);
        st.executeUpdate();
        
        
        st=con.prepareStatement(query2);
        st.setInt(1,client_id);
        st.executeUpdate();
        
        st=con.prepareStatement(query3);
        st.setInt(1,client_id);
        st.executeUpdate();
        
       
       
        System.out.println("client deleted successfully");
        
        con.close();
        st.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                  
		
	}


}

class ProductDevelopment extends Admin{
        String url="jdbc:mysql://localhost:3306/javaproject";
        String uname="root";
        String pass="123";
	
	void addProject(){
		
		System.out.println("************Product Development***********\n");
		System.out.println("***Enter details of product in development***");
		
		int new_product_id;
		String new_product_name;
		String dateofCompletion;
                String team_name;
		
		System.out.println("enter product id");
		new_product_id=vn.nextInt();
		vn.nextLine();
		System.out.println("enter name of the product");
		new_product_name=vn.next();
                
                if((new_product_name.length())==0){
			
			throw new InputMismatchException("  product name  cannot be blank");
			
		}
                
                
		vn.nextLine();
		System.out.println("Enter name of the team assigned ");
		 team_name=vn.nextLine();
                 
                 if((team_name.length())==0){
			
			throw new InputMismatchException("  team name address cannot be blank");
			
		}
                 
		vn.nextLine();
		System.out.println("Enter completion date");
		dateofCompletion=vn.nextLine();
                
                if((dateofCompletion.length())==0){
			
			throw new InputMismatchException("  completion date  cannot be blank");
			
		}
                
                
                
                try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
       
        String query1="insert into newproductdetails(new_product_id,name,team_name,completion_date)"+"values(?,?,?,?)";             
       
        //for table sales
        PreparedStatement nm=con.prepareStatement(query1);
        nm.setInt(1,new_product_id);
        nm.setString(2,new_product_name);
        nm.setString(3,team_name);
        nm.setString(4,dateofCompletion);
        
        int count=nm.executeUpdate();
        System.out.println(count +"row's affected in table new product  details");
        System.out.println(new_product_name+" has been added");
        
        nm.close();
        
        con.close();
        
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
                
		
		
	}
	
	void getNewProductDetail(){
		
		System.out.println("*********Retrieval of details of the project in Development***********\n");
		
		System.out.println("Enter product id of the product ");
                int new_product_id=vn.nextInt();
                
                
                 
        try{
            
        String query="select new_product_id,name,team_name,completion_date from newproductdetails where new_product_id= "+ new_product_id +" ";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        System.out.println("Product Details");
        System.out.println("id :"+rs.getInt("new_product_id")+" \nname of the product : "+rs.getString("name")+"\nDevelopment Team :"+rs.getString("team_name")+"\n completion date :"+rs.getString("completion_date"));
        
        con.close();
        st.close();
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found ex");
            
        }
        catch(SQLException e){
            
            System.out.println("Sql ex");
        }
             	
	}
	
}
	



class TesterProduct{

				public static void main(String args []){
					System.out.println("**************Welcome to  VMMV Software Solutions****************");
                                        
                                        
                                        System.out.println("Press 1 to continue");
                                        Client obj=new Client();
                                        System.out.println("Press 1 to view the Menu");
                                     
					Admin obj1=new Admin();
					
					
					System.out.println("************Please Select any of the option***************");
					
					
					System.out.println("1.Client\n2.Admin\n3.About Us");
                                        Scanner pq=new Scanner(System.in);
					int choice=pq.nextInt();
					
					switch(choice){
						
					case 1:	obj.clientMenu();
					break;

					case 2:	obj1.loginAdmin();
					break;
		
					case 3:
					obj1.aboutUs();
					break;
		
					default:
					System.out.println("Invalid Input");
					break;
					}
					
				}	
}
