package com.zm.utils.javautil;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hw.domain.BaseDomain;
import com.thoughtworks.xstream.XStream;

/**
 * @brief 仿IE的xml节点操作工具类。包括：<br/>
 *        <ul>
 *        <li>1. 选择单个节点</li>
 *        <li>2. 选择多个节点</li>
 *        <li>3. 获得节点内容</li>
 *        <li>4. 获得节点属性</li>
 *        <li>5. 将字符串转换为xml节点</li>
 *        </ul>
 */
public class XMLUtils {

	/** 通过xpath查询xml节点 */
	private static XPath xpath = XPathFactory.newInstance().newXPath();
	
	private static XStream xStream = new XStream();

	/**
	 * @brief 选择一个节点
	 * @param xmlNode
	 * @param path
	 * @return
	 */
	public static Node selectSingleNode(Node xmlNode, String path) {
		XPathExpression express;
		try {
			express = xpath.compile(path);
			NodeList nodes = (NodeList) express.evaluate(xmlNode,
					XPathConstants.NODESET);
			if (nodes.getLength() > 0) {
				return (Node) nodes.item(0);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @brief 选择多个节点
	 * @param xmlNode
	 * @param path
	 * @return
	 */
	public static NodeList selectNodes(Node xmlNode, String path) {
		XPathExpression express;
		try {
			express = xpath.compile(path);
			NodeList nodes = (NodeList) express.evaluate(xmlNode,
					XPathConstants.NODESET);
			return nodes;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @brief 获得节点内容
	 * @param xmlNode
	 * @return
	 */
	public static String getText(Node xmlNode) {
		if (xmlNode.getFirstChild() != null)
			return xmlNode.getFirstChild().getNodeValue();
		else
			return "";
	}

	/**
	 * @brief 获得xml节点的一个属性值，若属性不存在，则返回null
	 * @param xmlNode
	 *            带有属性的xml节点
	 * @param attrName
	 *            属性名
	 * @return
	 */
	public static String getAttribute(Node xmlNode, String attrName) {
		if (xmlNode == null)
			return null;
		NamedNodeMap attrs = xmlNode.getAttributes();
		if (attrs.getLength() > 0) {
			return getText(attrs.getNamedItem(attrName));
		}
		return null;
	}

	/**
	 * xml节点增加属性
	 * 
	 * @param xmlNode
	 *            xml节点
	 * @param attrName
	 *            属性名
	 * @param attrValue
	 *            属性值
	 */
	public static void addAttribute(Node xmlNode, String attrName,
			String attrValue) {
		Document doc = xmlNode.getOwnerDocument();
		Attr attr = doc.createAttribute(attrName);
		attr.setValue(attrValue);
		NamedNodeMap namedNodeMap = xmlNode.getAttributes();
		namedNodeMap.setNamedItem(attr);
	}

	public static void addAttributeNS(Element xmlNode, String attrName,
			String attrValue) {
		Document doc = xmlNode.getOwnerDocument();
		Attr attr = doc.createAttribute(attrName);
		attr.setValue(attrValue);
		xmlNode.setAttributeNodeNS(attr);
	}

	/**
	 * @brief 转换字符串为xml对象
	 * @param data
	 *            xml字符串
	 * @exception 格式不对解析异常
	 * @return
	 */
	public static Node converStringToXml(String data) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document = db
					.parse(new InputSource(new StringReader(data)));
			Node recordNode = document.getFirstChild();
			return recordNode.cloneNode(true);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String converXmlToString(Node data) {
		DOMSource source = new DOMSource(data);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS,
					"yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (writer.getBuffer().toString());
	}

	public static BaseDomain convertXmlToObj(Node dataNode, BaseDomain bean) {
		xStream.alias(dataNode.getNodeName(), bean.getClass());
		BaseDomain domain = null;
		domain = (BaseDomain) xStream.fromXML(XMLUtils
				.converXmlToString(dataNode));
		return domain;
	}

	public static Node convertObjToXml(String rootTag, BaseDomain bean) {
		xStream.alias(rootTag, bean.getClass());
		String sResult = xStream.toXML(bean);
		return XMLUtils.converStringToXml(sResult);
	}

	public static String convertJsonToXml(String json) {
		XMLSerializer serializer = new XMLSerializer();
		JSON jsonObject = JSONSerializer.toJSON(json);
		return serializer.write(jsonObject);
	}

	public static JSONObject convertXmlToJson(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject jsonObject = (JSONObject) xmlSerializer.read(xml);
		String jsonString = jsonObject.toString();
		jsonString = jsonString.replace("[]", "\"\"");
		return JSONObject.fromObject(jsonString);
	}
	
	public static JSONArray convertXmlToJsonarray(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONArray resultArray = null;
		try {
			if(xml == null || "".equals(xml)){
				return resultArray;
			}
			resultArray = (JSONArray) xmlSerializer.read(xml);
		} catch(Exception e) {
			resultArray = new JSONArray();
			JSONObject resultObj = XMLUtils.convertXmlToJson(xml);
			Iterator keys = resultObj.keys();
			if (keys.hasNext()) {
				String key = (String) keys.next();
				resultArray.add(resultObj.getJSONObject(key));
			}
		}
		return resultArray;
	}
}
