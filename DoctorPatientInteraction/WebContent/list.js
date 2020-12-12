
function fillCategory(){ 
 // this function is used to fill the category list on load
	addOption(document.drop_list.Category, "Select", "Select", "");
addOption(document.drop_list.Category, "Abacavir Sulfate (Ziagen)", "Abacavir Sulfate (Ziagen)", "");
addOption(document.drop_list.Category, "Abarelix (Plenaxis)", "Abarelix (Plenaxis)", "");
addOption(document.drop_list.Category, "Bacitracin (Bacitracin)", "Bacitracin (Bacitracin)", "");
addOption(document.drop_list.Category, "Balsalazide (Colazal)", "Balsalazide (Colazal)", "");
addOption(document.drop_list.Category, "Cabergoline (Dostinex)", "Cabergoline (Dostinex)", "");
addOption(document.drop_list.Category, "Calan (Verapamil HCl)", "Calan (Verapamil HCl)", "");
addOption(document.drop_list.Category, "Cafcit (Caffeine Citrate)", "Cafcit (Caffeine Citrate)", "");
addOption(document.drop_list.Category, "Dacarbazine (Dtic-Dome)", "Dacarbazine (Dtic-Dome)", "");

addOption(document.drop_list.Category1, "Select", "Select", "");
addOption(document.drop_list.Category1, "1", "1", "");
addOption(document.drop_list.Category1, "2", "2", "");
addOption(document.drop_list.Category1, "3", "3", "");
addOption(document.drop_list.Category1, "4", "4", "");
addOption(document.drop_list.Category1, "5", "5", "");
addOption(document.drop_list.Category1, "6", "6", "");
addOption(document.drop_list.Category1, "7", "7", "");
addOption(document.drop_list.Category1, "8", "8", "");



addOption(document.drop_list.Category3, "Select", "Select", "");
addOption(document.drop_list.Category3, "day", "day", "");
addOption(document.drop_list.Category3, "midday", "midday", "");
addOption(document.drop_list.Category3, "night", "night", "");
addOption(document.drop_list.Category3, "ALL", "ALL", "");



addOption(document.drop_list.Category2, "Select", "Select", "");
addOption(document.drop_list.Category2, "Before Meal", "Before Meal", "");
addOption(document.drop_list.Category2, "After Meal", "After Meal", "");
addOption(document.drop_list.Category2, "both", "both", "");


}



function SelectSubCat(){
// ON selection of category this function will work

removeAllOptions(document.drop_list.SubCat);
addOption(document.drop_list.SubCat, "", "SubCat", "");

if(document.drop_list.Category.value == 'FE1'){
addOption(document.drop_list.SubCat,"Mango", "Mango");
addOption(document.drop_list.SubCat,"Banana", "Banana");
addOption(document.drop_list.SubCat,"Orange", "Orange");
}
if(document.drop_list.Category.value == 'FE2'){
addOption(document.drop_list.SubCat,"Cricket", "Cricket");
addOption(document.drop_list.SubCat,"Football", "Football");
addOption(document.drop_list.SubCat,"Polo", "Polo", "");
}
if(document.drop_list.Category.value == 'SE3'){
addOption(document.drop_list.SubCat,"PHP", "PHP");
addOption(document.drop_list.SubCat,"ASP", "ASP");
addOption(document.drop_list.SubCat,"Perl", "Perl");
}

if(document.drop_list.Category.value == 'SE4'){
	addOption(document.drop_list.SubCat,"PHP", "PHP");
	addOption(document.drop_list.SubCat,"ASP", "ASP");
	addOption(document.drop_list.SubCat,"Perl", "Perl");
	}

if(document.drop_list.Category.value == 'TE5'){
	addOption(document.drop_list.SubCat,"PHP", "PHP");
	addOption(document.drop_list.SubCat,"ASP", "ASP");
	addOption(document.drop_list.SubCat,"Perl", "Perl");
	}

if(document.drop_list.Category.value == 'TE6'){
	addOption(document.drop_list.SubCat,"PHP", "PHP");
	addOption(document.drop_list.SubCat,"ASP", "ASP");
	addOption(document.drop_list.SubCat,"Perl", "Perl");
	}

if(document.drop_list.Category.value == 'BE7'){
	addOption(document.drop_list.SubCat,"PHP", "PHP");
	addOption(document.drop_list.SubCat,"ASP", "ASP");
	addOption(document.drop_list.SubCat,"Perl", "Perl");
	}
if(document.drop_list.Category.value == 'BE8'){
	addOption(document.drop_list.SubCat,"PHP", "PHP");
	addOption(document.drop_list.SubCat,"ASP", "ASP");
	addOption(document.drop_list.SubCat,"Perl", "Perl");
	}


}
////////////////// 

function removeAllOptions(selectbox)
{
	var i;
	for(i=selectbox.options.length-1;i>=0;i--)
	{
		//selectbox.options.remove(i);
		selectbox.remove(i);
	}
}


function addOption(selectbox, value, text )
{
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;

	selectbox.options.add(optn);
}
