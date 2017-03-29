		<!-- Load Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<!-- Load Static CSS -->
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		
		<!-- Load Font Awesome CSS -->
		<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
	</head>
	<!-- End Head Tag -->
	
	<!-- Open Body Tag -->
	<body ng-app="myApp" class="ng-cloak" ng-controller="MainController as mainCtrl" ng-class="{'popinDisplay': (mainCtrl.bodyClassOverflow)}">
		 <div ng-controller="CardController as ctrl">
  			<div ng-controller="EditionController as ctrlE">
  				<div ng-controller="RarityController as ctrlR">
  					<div ng-controller="TypeController as ctrlT">
  						<div ng-controller="CarddeckController as ctrlCD">