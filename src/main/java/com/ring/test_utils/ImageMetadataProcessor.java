package com.ring.test_utils;

import org.w3c.dom.NamedNodeMap;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

class ImageMetadataProcessor {

    static String readMetadata(URL imageURL) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(imageURL.openStream());
        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
        StringBuilder result = new StringBuilder();

        if (readers.hasNext()) {

            // pick the first available ImageReader
            ImageReader reader = readers.next();

            // attach source to the reader
            reader.setInput(iis, true);

            // read metadata of first image
            IIOMetadata metadata = reader.getImageMetadata(0);

            String[] names = metadata.getMetadataFormatNames();
            for (String name : names) {
                writeMetadata(metadata.getAsTree(name), result);
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private static void writeMetadata(org.w3c.dom.Node root, StringBuilder result) {
        writeMetadata(root, 0, result);
    }

    private static void indent(int level) {
        for (int i = 0; i < level; i++)
            System.out.print("    ");
    }

    private static void writeMetadata(org.w3c.dom.Node node, int level, StringBuilder result) {
        // print open tag of element
        indent(level);
        result.append("<").append(node.getNodeName());
        NamedNodeMap map = node.getAttributes();
        if (map != null) {

            // print attribute values
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                org.w3c.dom.Node attr = map.item(i);
                result.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
            }
        }

        org.w3c.dom.Node child = node.getFirstChild();
        if (child == null) {
            // no children, so close element and return
            result.append("/>");
            return;
        }

        // children, so close current tag
        result.append(">");
        while (child != null) {
            // print children recursively
            writeMetadata(child, level + 1, result);
            child = child.getNextSibling();
        }

        // print close tag of element
        indent(level);
        result.append("</").append(node.getNodeName()).append(">");
    }
}
