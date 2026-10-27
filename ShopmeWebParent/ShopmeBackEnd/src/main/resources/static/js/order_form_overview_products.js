var fieldProductCost;
var fieldSubtotal;
var fieldShippingCost;
var fieldTax;
var fieldTotal;

$(document).ready(function() {
	
	fieldProductCost = $("#productCost");
	fieldSubtotal = $("#subtotal");
	fieldShippingCost = $("#shippingCost");
	fieldTax = $("#tax");
	fieldTotal = $("#total");
	
	formatOrderAmounts();
	formatProductAmounts();
	
	$("#productList").on("change", ".quantity-input", function(e) {
		updateSubtotalWhenQuantityChanges($(this));
		updateOrderAmounts();
	});
	
	$("#productList").on("change", ".price-input", function(e) {
			updateSubtotalWhenPriceChanges($(this));
			updateOrderAmounts();
	});
	$("#productList").on("change", ".cost-input", function(e) {	
			updateOrderAmounts();
	});
	$("#productList").on("change", ".ship-input", function(e) {	
			updateOrderAmounts();
	});
});

function updateOrderAmounts(){
	totalCost = 0.0;
	
	$(".cost-input").each(function(e){
		costInputField = $(this);
		rowNumber = costInputField.attr("rowNumber");
		quantityValue = $("#quantity" + rowNumber).val();
		
		productCost = getNumberValueRemoveThousandSeparator(costInputField) * quantityValue;
		totalCost += productCost * parseInt(quantityValue);
	});
	
	setAndFormatNumberForField("productCost", totalCost);
	
	orderSubtotal = 0.0;
	
	$(".subtotal-output").each(function(e) {
		pruductSubtotal = getNumberValueRemoveThousandSeparator($(this));
		orderSubtotal += pruductSubtotal;
	});
	
	setAndFormatNumberForField("subtotal", orderSubtotal);
	
	shippingCost = 0.0;
		
	$(".ship-input").each(function(e) {
		pruductShip = getNumberValueRemoveThousandSeparator($(this));
		shippingCost += pruductShip;
	});
	
	setAndFormatNumberForField("shippingCost", shippingCost);
	
	tax = getNumberValueRemoveThousandSeparator(fieldTax);
	orderTotal = orderSubtotal + tax + shippingCost;
	setAndFormatNumberForField("total", orderTotal);

}

function setAndFormatNumberForField(fieldId, fieldValue){
	formatedValue = $.number(fieldValue, 2);
	$("#" + fieldId).val(formatedValue);
}

function getNumberValueRemoveThousandSeparator(fieldRef){
	fieldValue = fieldRef.val().replace(",", "");
	return parseFloat(fieldValue);
}

function updateSubtotalWhenPriceChanges(input){
	quantityValue =  getNumberValueRemoveThousandSeparator(input);
	rowNumber = input.attr("rowNumber");
	
	quantityField = $("#quantity" + rowNumber);
	quantityValue = quantityField.val();
	newSubtotal = parseFloat(quantityValue) * priceValue;
	
	setAndFormatNumberForField("subtotal" + rowNumber, newSubtotal);
}

function updateSubtotalWhenQuantityChanges(input){
	quantityValue = input.val();
	rowNumber = input.attr("rowNumber");
	priceValue =  getNumberValueRemoveThousandSeparator($("#price" + rowNumber));
	newSubtotal = parseFloat(quantityValue) * priceValue;
	
	setAndFormatNumberForField("subtotal" + rowNumber, newSubtotal);
}

function formatProductAmounts(){
	$(".cost-input").each(function(e){
		formatNumberForField($(this));	
	});
	$(".price-input").each(function(e){
		formatNumberForField($(this));	
	});
	$(".subtotal-otput").each(function(e){
		formatNumberForField($(this));	
	});
	$(".ship-input").each(function(e){
		formatNumberForField($(this));	
	});
}

function formatOrderAmounts(){
	formatNumberForField(fieldProductCost);
	formatNumberForField(fieldSubtotal);
	formatNumberForField(fieldShippingCost);
	formatNumberForField(fieldTax);
	formatNumberForField(fieldTotal);
}

function formatNumberForField(fieldRef){
	fieldRef.val($.number(fieldRef.val(), 2));
}