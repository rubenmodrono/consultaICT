
var selection;
function toggleSelection(id){
	if (selection!=id){
		document.getElementById("optSelected").value=id;
		selection=id;
	}else{
		document.getElementById("optSelected").value=4;
	}
};

function getCategoriesChecked(){
	var list=tree.getAllChecked();
	document.getElementById("categoriesSelected").value=list;
	document.getElementById("searchForm").submit();
};

function getPlanData(id){
	
	 $.getJSON("getRecordData.do", { reportId: id }, function(viewBean) {
		 		
		 		$( '#dialog' ).empty();
		  		$( '<p>' + viewBean.view.activityDescription + '</p>' ).appendTo('#dialog');
	
		  		if (viewBean.hits.length>0){
		  			$( '<p><strong>Categories checked</strong></p><p><ul>' ).appendTo('#dialog');
			  		for (var i=0 ; i<viewBean.hits.length ; i++ ){
			  			$('<li>'+ viewBean.hits[i].quickLabel+'</li>').appendTo('#dialog');
			  			if (i==viewBean.hits.length-1){
			  				$('</ul>').appendTo('#dialog');
			  			}
			  		}
		  				
		  		}
		  		
		  		$( viewBean.mediaHTML).appendTo('#dialog');
		  		//$('<p><a target="_new" href="http://localhost:8080/ActivityAssistant/WorkbookServlet?editionId=BUS&liveContentId='
		  		//		+id+
		  		//	'&referer=CategoryView.act&refererName=workbook%20categories%20view" ><strong>workbook link</strong></a>').appendTo('#dialog');
		  		$( '#dialog' )
		  			.dialog( 'open')
		  			.dialog("option","width",600)
		  			.dialog("option","resizable",false)
		  			.dialog("option","position",'center')
		  			.dialog("option","title",viewBean.view.activityTitle.toUpperCase());

		 		return false;
		     });

	
}