package com.pushpop.client.view.desktop;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.client.proxy.TagProxy;

public class QuestionCell extends AbstractCell<QuestionProxy> {

    public static interface QuestionTemplate extends SafeHtmlTemplates {
        
        @SafeHtmlTemplates.Template(
            "<div>" +
            "<div class=\"votes\">" +
                "<div class=\"mini-counts\">{0}</div>" +
                "<div>votes</div>" +
            "</div>" +
            "<div class=\"status unanswered\">" +
                "<div class=\"mini-counts\">{1}</div>" +
                "<div>answers</div>" +
            "</div>" +
            "<div class=\"views\">" +
                "<div class=\"mini-counts\">{2}</div>" +
                "<div>views</div>" +
            "</div>" +
        "</div>")
        SafeHtml questionStats(String voteCount, String answersCount, String viewCount);
        
        
        @SafeHtmlTemplates.Template(
             "<h3>" +
                "<a title=\"{0}\" class=\"question-hyperlink\" href=\"#QuestionPlace:Q={1}\">" +
                    "{2}" +
                "</a>" +
            "</h3>")
        SafeHtml questionTitle(String questionDescription, String questionId, String questionTitle);
        
        
        @SafeHtmlTemplates.Template(          
                "<div class=\"started\">" +
                        "<a class=\"started-link\" href=\"/#QuestionPlace:Q={0}\"> " +
                            "<span class=\"relativetime\" title=\"{1}\">{1}</span>" +
                        "</a>" +
                        "<a href=\"/users/{2}/rs\">{3}</a>" +
                        "<span dir=\"ltr\" title=\"reputation score\" class=\"reputation-score\">{4}</span>" +
                "</div>")
        SafeHtml started(String questionId, String openedDate, String owernId, String ownerName, String ownerReputationScore);
    }
    

    /**
     * <a rel="tag" title="show questions tagged 'dataview'" class="post-tag"
                    href="/questions/tagged/dataview">dataview</a>
     * @author chinshaw
     *
     */
    public static interface TagTemplate extends SafeHtmlTemplates  {
        @SafeHtmlTemplates.Template("<a rel=\"tag\" title=\"show questions tagged '{0}'\" class=\"post-tag\" " +
        		"href=\"#questions:t={1}\">{0}</a>")
        SafeHtml createTag(String name, String id);

    }
    
    /*
     * <ui:with field="votesCount" type="java.lang.Integer" />
     * <ui:with field="answersCount" type="java.lang.Integer" />
     * <ui:with field="viewsCount" type="java.lang.Integer" />
     * <ui:with field="questionDescription" type="java.lang.String" />
     * <ui:with field="questionTitle" type="java.lang.String" />
     * <ui:with field="questionOpenedDate" type="java.util.Date" />
     * <ui:with field="ownerReputationScore" type="java.lang.Integer" />
     
    public interface Renderer extends UiRenderer {
        
        void render(SafeHtmlBuilder sb, String votesCount, String answersCount, String viewsCount, String questionDescription, String questionTitle, String questionOpenedDate, String ownerName, String ownerReputationScore);
        
        void onBrowserEvent(QuestionCell o, NativeEvent e, Element p, QuestionProxy n);
    
        public DivElement getTagsContainer(Element parent);
        
        public Element getRoot();
    }
    */

    //private final Renderer renderer;

    public QuestionCell() {
        super(BrowserEvents.CLICK);
        //renderer = GWT.<Renderer> create(Renderer.class);
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, QuestionProxy value, NativeEvent event, ValueUpdater<QuestionProxy> valueUpdater) {
        //renderer.onBrowserEvent(this, event, parent, value);
    }

    @Override
    public void render(Context context, QuestionProxy question, SafeHtmlBuilder sb) {
        if (question == null) {
            return;
        }
        
        String ownerName = question.getOwner().getCommonName();
        
        Integer reputationScore = 100;
        
        QuestionTemplate template = GWT.create(QuestionTemplate.class);
        
        // Statistics
        sb.appendHtmlConstant("<div class=\"question-summary narrow\">");
        sb.append(template.questionStats(question.getViewsCount().toString(), question.getAnswersCount().toString(), question.getViewsCount().toString()));
        
        //Summary
        sb.appendHtmlConstant("<div class=\"summary\">");
        sb.append(template.questionTitle(question.getDescription(), question.getId().toString(), question.getTitle()));
        sb.appendHtmlConstant("</div>");
        
        // Tags
        if (question.getTags() != null) {
            TagTemplate tagTemplate = GWT.create(TagTemplate.class);
            sb.appendHtmlConstant("<div class=\"tags t-formula t-dataview\">");
            for (TagProxy tag : question.getTags()) {
                tagTemplate.createTag(tag.getName(), tag.getId().toString());
            }
            sb.appendHtmlConstant("</div>");
        }
        
        // Owner and reputation
        sb.append(template.started(question.getId().toString(), 
                question.getOpenedTimeStamp().toString(), 
                question.getOwner().getId().toString(), 
                question.getOwner().getCommonName(), 
                question.getOwner().getReputation().getReputationScore().toString()));
       
        
        // Closing
        sb.appendHtmlConstant("</div>");
        
        //GWT.log(" " + question.getVotesCount().toString() + " , " + question.getAnswersCount().toString() + " , " + question.getViewsCount().toString() + ", "
        //+ question.getDescription() + " , "  +question.getTitle() + " , " + question.getOpenedTimeStamp().toString());
        
        //renderer.render(sb, question.getVotesCount().toString(), question.getAnswersCount().toString(), question.getViewsCount().toString(), question.getDescription(), question.getTitle(), question.getOpenedTimeStamp().toString() , ownerName, reputationScore.toString());        
    }
}
