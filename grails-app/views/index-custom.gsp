<!DOCTYPE html>
<html>
	<head>
        <meta name="layout" content="main-custom"/>
		<title> Grails app</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}
            #countyAndPlace{
                width: 775px;
                /*margin: auto;*/
            }
            #county{
                float:left;
                width:350px;
            }
            #place{
                float:right;
                width:425px;
            }
            #ancestry-block{
                margin-top: 1em;
            }
            #clear{
                clear:both;
            }
            #results{
                margin-top:1em;
            }
			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
       <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"/> -->
        <script src="js/simple.js"></script>
	</head>
	<body>

    <div id="page-body" role="main">
			<h1>Geo-Ancestry app</h1><h2>Total Ancestry Reported from 3-year estimates 2011-2013</h2>
            <div>
                <p>Select a State to get started:</p>
                <select id="states">
                    <option value="-1" selected="selected">Select...</option>
                </select>
            </div>
        <div id="countyAndPlace">
            <div id="county">
                <p>Now select a County:</p>
                <select id="counties">
                    <option value="-1" selected="selected">Select...</option>
                </select>
            </div>
            <div id="place">
                <p><b>OR</b> a Place:</p>
                <select id="places">
                    <option value="-1" selected="selected">Select...</option>
                </select>
            </div>
            <div id="clear"></div>
        </div>
        <div id="ancestry-block">
            <p>Finally, select an Ancestry:</p>
            <select id="ancestry">
                <option value="-1" selected="selected">Select...</option>
            </select>
        </div>
            <div id="results">
                <p></p>
            </div>
		</div>
	</body>
</html>
