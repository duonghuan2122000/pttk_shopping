<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a onclick="clickNavHome()" class="navbar-brand" href="#">Shopping</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a onclick="clickNavHome()"
					class="nav-link active" aria-current="page" href="#">Home</a></li>
				<li class="nav-item"><a onclick="clickNavBook()"
					class="nav-link active" href="#">Book</a></li>
				<li class="nav-item"><a onclick="clickNavElectronic()"
					class="nav-link active" href="#">Electronic</a></li>
				<li class="nav-item"><a onclick="clickNavClothes()"
					class="nav-link active" href="#">Clothes</a></li>
				<li class="nav-item"><a onclick="clickNavShoes()"
					class="nav-link active" href="#">Shoes</a></li>
				<li id="manager-controller" class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						For employee </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a onclick="clickNavOrder()" class="dropdown-item" href="#">Order</a></li>
						<li><a onclick="clickNavAccount()" class="dropdown-item" href="#">Account</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
					</ul></li>
					
				<li id="customer-controller" class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Customer </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a onclick="clickNavCart()" class="dropdown-item" href="#">Cart</a></li>
						<li><a onclick="clickNavOrder()" class="dropdown-item" href="#">Order</a></li>
					</ul></li>
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
			<button onclick="clickNavLogin()" id="login-btn" type="button"
				class="btn btn-light">Login</button>
			<button onclick="clickNavLogout()" id="logout-btn" type="button"
				class="btn btn-light">Logout</button>
		</div>
	</div>
</nav>