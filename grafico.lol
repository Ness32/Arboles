digraph "G" {
	size ="10,6";
	ratio=auto;
	graph [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
	]
	node  [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		shape = "record"
		style = "solid"
	]
	edge [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"d" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>d|<f2>"
		shape = "record"
		fillcolor = "lightblue"
		style = "filled"
	]
	"b" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>b|<f2>"
		shape = "record"
		fillcolor = "lightblue"
		style = "filled"
	]
	"c" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>c|<f2>"
		shape = "record"
		fillcolor = "greenyellow"
		style = "filled"
	]
	"HAiro" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>HAiro|<f2>"
		shape = "record"
		fillcolor = "lightblue"
		style = "filled"
	]
	"g" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>g|<f2>"
		shape = "record"
		fillcolor = "lightblue"
		style = "filled"
	]
	"f" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>f|<f2>"
		shape = "record"
		fillcolor = "greenyellow"
		style = "filled"
	]
	"h" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>h|<f2>"
		shape = "record"
		fillcolor = "greenyellow"
		style = "filled"
	]
	"j" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>j|<f2>"
		shape = "record"
		fillcolor = "lightblue"
		style = "filled"
	]
	"i" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>i|<f2>"
		shape = "record"
		fillcolor = "greenyellow"
		style = "filled"
	]
	"k" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		label = "<f0> |<f1>k|<f2>"
		shape = "record"
		fillcolor = "greenyellow"
		style = "filled"
	]
	"d":f2 -> "HAiro" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"d":f0 -> "b" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"b":f2 -> "c" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"HAiro":f2 -> "j" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"HAiro":f0 -> "g" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"g":f2 -> "h" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"g":f0 -> "f" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"j":f2 -> "k" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]
	"j":f0 -> "i" [
		fontsize = "14"
		fontname = "Times-Roman"
		fontcolor = "black"
		style = "solid"
	]

}
