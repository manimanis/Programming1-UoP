import codecs
import json
import os
import pprint
from bs4 import BeautifulSoup


style = """<style>
  .questions {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }

  .question {
    width: 49%;
    margin: 0.25rem 0;
    page-break-inside: avoid;
  }

  h1, h2, h3, h4, p {
    margin: 0.25em 0;
  }

  .answer {
    display: flex;
    align-items: center;
  }

  img {
    max-width: 9cm;
  }
</style>"""


def getText(txt: str):
    return " ".join(txt.strip().split())


def extract_paragraphs(elem):
    pars = elem.find_all("p")
    partext = ""
    for par in pars:
        if partext:
            partext += "\n"
        partext += getText(str(par.prettify()))
    if partext == "":
        partext = getText(str(elem.prettify()))
    return partext


folder = "math1201"
curfolder = os.path.dirname(__file__)

html_files = {}
folder_path = os.path.join(curfolder, folder)
for file in os.listdir(folder_path):
    path = os.path.join(folder_path, file)
    if os.path.isdir(path):
        html_files[file] = []
        for afile in os.listdir(path):
            filepath = os.path.join(path, afile)
            if (not afile.startswith("f-")) and afile.endswith(".html"):
                html_files[file].append(filepath)

all_questions = []
for unit in html_files.keys():
    unit_questions = []
    for file in html_files[unit]:
        print(file)
        
        dirname = os.path.dirname(file)
        filename = os.path.basename(file)
        newfile = os.path.join(dirname, "f-" + filename)
        if os.path.exists(newfile):
            os.unlink(newfile)

        with open(file, "r", encoding="utf8") as f:
            text = f.read()
        of = open(newfile, "w", encoding="utf-8")
        of.write(style)
        bs = BeautifulSoup(text, "lxml")
        questions = bs.select(".que")
        of.write("<div class=\"questions\">")
        for question in questions:
            of.write("<div class=\"question\">")
            qnum = question.select_one(".info > .no")
            of.write("<h3>" + qnum.text + "</h3>")
            content = question.select_one(".content")
            qtext = content.select_one(".qtext")
            fieldset = content.find("fieldset")
            legend = fieldset.find("legend").text
            if legend.startswith(qnum.text):
                legend = legend[len(qnum.text):]
            of.write("<div class=\"qtext\">" + qtext.prettify() + 
                     "<p>" + legend + "</p>" +
                     "</div>")
            of.write("<div class=\"answers\">")
            answers = fieldset.select(".answer > div")
            for answer in answers:
                of.write("<div class=\"answer\">")
                an = answer.select_one(".answernumber")
                if an:
                    of.write(answer.select_one(".answernumber").text + "&nbsp;")
                answer_content = answer.select_one(".flex-fill.ml-1")
                if answer_content:
                    of.write(answer_content.prettify())
                else:
                    of.write(answer.prettify())
                of.write("</div>")  # class=\"answer\"
            of.write("</div>")  # class=\"answers\"
            of.write("</div>")  # class=\"question\"
        of.write("</div>")  # class=\"questions\"
        of.close()
