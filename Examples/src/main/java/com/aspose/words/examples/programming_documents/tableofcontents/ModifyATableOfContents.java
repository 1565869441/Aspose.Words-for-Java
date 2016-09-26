package com.aspose.words.examples.programming_documents.tableofcontents;

import com.aspose.words.Document;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.StyleIdentifier;
import com.aspose.words.TabStop;
import com.aspose.words.examples.Utils;

public class ModifyATableOfContents {

	private static final String dataDir = Utils.getSharedDataDir(ModifyATableOfContents.class) + "TableOfContents/";

	public static void main(String[] args) throws Exception {
		changeAFormattingPropertyUsedInFirstLevelTOCStyle();
		
		modifyPositionOfRightTabStopInTOC();
	}	

	public static void changeAFormattingPropertyUsedInFirstLevelTOCStyle() throws Exception {
		// ExStart:ChangeAFormattingPropertyUsedInFirstLevelTOCStyle
		Document doc = new Document();
		// Retrieve the style used for the first level of the TOC and change the formatting of the style.
		doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_1).getFont().setBold(true);
		// ExEnd:ChangeAFormattingPropertyUsedInFirstLevelTOCStyle
	}

	public static void modifyPositionOfRightTabStopInTOC() throws Exception {
		// ExStart:ModifyPositionOfRightTabStopInTOC
		Document doc = new Document(dataDir + "Field.TableOfContents.doc");

		// Iterate through all paragraphs in the document
		for (Paragraph para : (Iterable<Paragraph>) doc.getChildNodes(NodeType.PARAGRAPH, true)) {
			// Check if this paragraph is formatted using the TOC result based styles. This is any style between TOC and TOC9.
			if (para.getParagraphFormat().getStyle().getStyleIdentifier() >= StyleIdentifier.TOC_1 && para.getParagraphFormat().getStyle().getStyleIdentifier() <= StyleIdentifier.TOC_9) {
				// Get the first tab used in this paragraph, this should be the tab used to align the page numbers.
				TabStop tab = para.getParagraphFormat().getTabStops().get(0);
				// Remove the old tab from the collection.
				para.getParagraphFormat().getTabStops().removeByPosition(tab.getPosition());
				// Insert a new tab using the same properties but at a modified position.
				// We could also change the separators used (dots) by passing a different Leader type
				para.getParagraphFormat().getTabStops().add(tab.getPosition() - 50, tab.getAlignment(), tab.getLeader());
			}
		}

		doc.save(dataDir + "Field.TableOfContentsTabStops_Out.doc");
		// ExEnd:ModifyPositionOfRightTabStopInTOC
	}
}