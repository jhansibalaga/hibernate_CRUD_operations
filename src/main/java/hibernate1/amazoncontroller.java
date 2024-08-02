package hibernate1;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class amazoncontroller {
	 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ch;
		do {
			display();
			
			System.out.println("enter your choice");
			ch=Integer.parseInt(sc.nextLine());
			switch (ch) {
			case 1:
				insertion();
				break;
			case 2:
				update();
				break;
			case 3:
				fetchall();
				break;
			case 4:
				fetchbyid();
				break;
			case 5:
				deletebyid();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				 System.out.println("invalid operation");	
				 break;

			
			}
		} while (ch>0);
	}

	private static void deletebyid() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = mt.buildSessionFactory();
		Session s = sf.openSession();//all crud operations are present
		System.out.println("enter id value:");
		int id = sc.nextInt();
		Transaction t = s.beginTransaction();		
		amazon a = s.get(amazon.class, id);
		s.delete(a);
		t.commit();
		System.out.println("successfully deleted");
		
		
	}

	private static void fetchbyid() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = mt.buildSessionFactory();
		Session s = sf.openSession();//all crud operations are present
		System.out.println("enter id value:");
		int id = sc.nextInt();
		Transaction t = s.beginTransaction();		
		amazon a = s.get(amazon.class, id);
		if(a!=null) {
			System.out.println("id: "+a.getId());
			System.out.println("name: "+a.getName() );
			System.out.println("email: "+a.getEmail());
			System.out.println("password: "+a.getPassword());
		}
		else {
			System.out.println("sorry! unable to fetch");
		}		
		t.commit();				
	}

	private static void fetchall() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = mt.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		List<amazon> li = s.createQuery("from amazon",amazon.class).list();
		
		t.commit();
		
		//when the data can display in the console we have to use for loop
		for(amazon a:li) {
			
			System.out.println("id: "+a.getId());
			System.out.println("name: "+a.getName());
			System.out.println("email: "+a.getEmail());
			System.out.println("password: "+a.getPassword());
			
		}
		
	}

	private static void update() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = mt.buildSessionFactory();
		Session s = sf.openSession();//all crud operations are present
		System.out.println("enter id value:");
		int id = sc.nextInt();
		Transaction t = s.beginTransaction();
		amazon a = s.get(amazon.class, id);
		if(a!=null) {
			System.out.println("enter new name:");
			String name = sc.next();
			System.out.println("enter new email:");
			String email = sc.next();
			System.out.println("enter new password:");
			String password = sc.next();
			a.setName(name);
			a.setEmail(email);
			a.setPassword(password);			
			s.update(a);	
			System.out.println("successfully updated");
		}
		else {
			System.out.println("couldn't find the data");
		}
		
		t.commit();			
		
	}

	private static void insertion() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = mt.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		amazon a = new amazon();
		System.out.println("enter name");
		a.setName(sc.next());
		System.out.println("enter email");
		a.setEmail(sc.next());
		System.out.println("enter password");
		a.setPassword(sc.next());
		s.save(a);
		t.commit();
		System.out.println("successfully deleted");
	}

	private static void display() {
		System.out.println("-------------------------");
		System.out.println("\t1.insertion");
		System.out.println("\t2.update");
		System.out.println("\t3.fetchall");
		System.out.println("\t4.fetchbyid");
		System.out.println("\t5.deletebyid");
		System.out.println("\t6.exit");
		System.out.println("--------------------------");
		
	}

}
