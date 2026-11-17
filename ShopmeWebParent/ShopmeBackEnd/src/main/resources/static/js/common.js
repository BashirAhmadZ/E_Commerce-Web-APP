function getBootstrapModal(element) {
	var modalElement = element instanceof HTMLElement ? element : document.querySelector(element);
	if (!modalElement) return null;
	return bootstrap.Modal.getOrCreateInstance(modalElement);
}
function showBootstrapModal(selector) { var modal = getBootstrapModal(selector); if (modal) modal.show(); }
function hideBootstrapModal(selector) { var modal = getBootstrapModal(selector); if (modal) modal.hide(); }

$(document).ready(function() {
	$("#logoutLink").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
	});
	
	customizeDropDownMenu();
	customizeTabs();
});

function customizeDropDownMenu() {
	$(".navbar .dropdown").hover(
		function() {
			$(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
		},
		function(){
			$(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();
		}
	);
	$(".dropdown > a").click(function () {
		location.href = this.href;
	});
}

function customizeTabs() {
	//Javascript to enable link to tab
	var url = document.location.toString();
	if(url.match('#')) {
		var tab = document.querySelector('.nav-tabs a[href="#' + url.split('#')[1] + '"]'); if (tab) bootstrap.Tab.getOrCreateInstance(tab).show();
	}
	
	//Change hash for page-reload
	$('.nav-tabs a').on('shown.bs.tab', function (e) {
		window.location.hash = e.target.hash;
	});
}

function handleDefaultDetailLinkClick() {
  $(".link-detail").on("click", function(e) {
    e.preventDefault();
    var modal = document.querySelector("#detailModal");
    if (!modal) return;
    $(modal).find(".modal-content").load($(this).attr("href"), function() { showBootstrapModal(modal); });
  });
}

