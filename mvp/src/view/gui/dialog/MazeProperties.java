package view.gui.dialog;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 * Window that let you choose the properties for the maze.
 *
 */
public class MazeProperties {

	private Shell shell;
	private Button set;


	public MazeProperties(Shell parent, int style, int width, int height) {

		shell = new Shell(parent, style);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(width, height);
		shell.setText("Properties");

		Group numOfThreads = new Group(shell, SWT.SHADOW_OUT);
		numOfThreads.setText("Number of Threads");
		numOfThreads.setLayout(new GridLayout(3, true));
		Group interfaceView = new Group(shell, SWT.SHADOW_OUT);
		interfaceView.setText("Interface View");
		interfaceView.setLayout(new GridLayout(2, true));
		Group searchAlgo = new Group(shell, SWT.SHADOW_OUT);
		searchAlgo.setText("Search Algorithm");
		searchAlgo.setLayout(new GridLayout(2, true));

		Button one = new Button(numOfThreads, SWT.RADIO);
		one.setText("1");
		one.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("numOfThreads").item(0).getFirstChild().setNodeValue("1");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);

				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
		Button two = new Button(numOfThreads, SWT.RADIO);
		two.setText("2");
		two.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("numOfThreads").item(0).getFirstChild().setNodeValue("2");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
		Button three= new Button(numOfThreads, SWT.RADIO);
		three.setText("3");
		three.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("numOfThreads").item(0).getFirstChild().setNodeValue("3");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			
			}
		});
		

		Button cli=new Button(interfaceView, SWT.RADIO);
		cli.setText("CLI");
		cli.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("interfaceView").item(0).getFirstChild().setNodeValue("CLI");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
		
			}
		});
		Button gui=new Button(interfaceView, SWT.RADIO);
		gui.setText("GUI");
		gui.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("interfaceView").item(0).getFirstChild().setNodeValue("GUI");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			
			}
		});

		Button bfs=new Button(searchAlgo, SWT.RADIO);
		bfs.setText("BFS");
		bfs.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("searchAlgo").item(0).getFirstChild().setNodeValue("BFS");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});		
		Button aStar=new Button(searchAlgo, SWT.RADIO);
		aStar.setText("AStar");
		aStar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					File inputFile = new File("properties.xml");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					doc.getElementsByTagName("searchAlgo").item(0).getFirstChild().setNodeValue("AStar");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult("properties.xml");
					transformer.transform(source, result);
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {

					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
		
			}
		});

		set = new Button(shell, SWT.MODELESS);
		set.setText("Set");
		set.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		set.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

	}

	public void setSelectionListener(SelectionListener selectedEvent) {
		set.addSelectionListener(selectedEvent);
	}

	public void showPropertiesDialog() {
		shell.pack();
		shell.open();
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

}
