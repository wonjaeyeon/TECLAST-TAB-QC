package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.oss_data

data class LicenseDescription(
    val id: Int,
    val License: String,
    val Description: String,
    val link: String
)

class LDRepository {
    fun getAllData(): List<LicenseDescription> {
        return listOf(
//            LicenseDescription(
//                id = 0,
//                License = "Apache License 2.0",
//                Description =
//                "Version 2.0, January 2004\n" +
//                        "http://www.apache.org/licenses/\n" +
//                        "\n" +
//                        "   TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n" +
//                        "\n" +
//                        "   1. Definitions.\n" +
//                        "\n" +
//                        "      \"License\" shall mean the terms and conditions for use, reproduction,\n" +
//                        "      and distribution as defined by Sections 1 through 9 of this document.\n" +
//                        "\n" +
//                        "      \"Licensor\" shall mean the copyright owner or entity authorized by\n" +
//                        "      the copyright owner that is granting the License.\n" +
//                        "\n" +
//                        "      \"Legal Entity\" shall mean the union of the acting entity and all\n" +
//                        "      other entities that control, are controlled by, or are under common\n" +
//                        "      control with that entity. For the purposes of this definition,\n" +
//                        "      \"control\" means (i) the power, direct or indirect, to cause the\n" +
//                        "      direction or management of such entity, whether by contract or\n" +
//                        "      otherwise, or (ii) ownership of fifty percent (50%) or more of the\n" +
//                        "      outstanding shares, or (iii) beneficial ownership of such entity.\n" +
//                        "\n" +
//                        "      \"You\" (or \"Your\") shall mean an individual or Legal Entity\n" +
//                        "      exercising permissions granted by this License.\n" +
//                        "\n" +
//                        "      \"Source\" form shall mean the preferred form for making modifications,\n" +
//                        "      including but not limited to software source code, documentation\n" +
//                        "      source, and configuration files.\n" +
//                        "\n" +
//                        "      \"Object\" form shall mean any form resulting from mechanical\n" +
//                        "      transformation or translation of a Source form, including but\n" +
//                        "      not limited to compiled object code, generated documentation,\n" +
//                        "      and conversions to other media types.\n" +
//                        "\n" +
//                        "      \"Work\" shall mean the work of authorship, whether in Source or\n" +
//                        "      Object form, made available under the License, as indicated by a\n" +
//                        "      copyright notice that is included in or attached to the work\n" +
//                        "      (an example is provided in the Appendix below).\n" +
//                        "\n" +
//                        "      \"Derivative Works\" shall mean any work, whether in Source or Object\n" +
//                        "      form, that is based on (or derived from) the Work and for which the\n" +
//                        "      editorial revisions, annotations, elaborations, or other modifications\n" +
//                        "      represent, as a whole, an original work of authorship. For the purposes\n" +
//                        "      of this License, Derivative Works shall not include works that remain\n" +
//                        "      separable from, or merely link (or bind by name) to the interfaces of,\n" +
//                        "      the Work and Derivative Works thereof.\n" +
//                        "\n" +
//                        "      \"Contribution\" shall mean any work of authorship, including\n" +
//                        "      the original version of the Work and any modifications or additions\n" +
//                        "      to that Work or Derivative Works thereof, that is intentionally\n" +
//                        "      submitted to Licensor for inclusion in the Work by the copyright owner\n" +
//                        "      or by an individual or Legal Entity authorized to submit on behalf of\n" +
//                        "      the copyright owner. For the purposes of this definition, \"submitted\"\n" +
//                        "      means any form of electronic, verbal, or written communication sent\n" +
//                        "      to the Licensor or its representatives, including but not limited to\n" +
//                        "      communication on electronic mailing lists, source code control systems,\n" +
//                        "      and issue tracking systems that are managed by, or on behalf of, the\n" +
//                        "      Licensor for the purpose of discussing and improving the Work, but\n" +
//                        "      excluding communication that is conspicuously marked or otherwise\n" +
//                        "      designated in writing by the copyright owner as \"Not a Contribution.\"\n" +
//                        "\n" +
//                        "      \"Contributor\" shall mean Licensor and any individual or Legal Entity\n" +
//                        "      on behalf of whom a Contribution has been received by Licensor and\n" +
//                        "      subsequently incorporated within the Work.\n" +
//                        "\n" +
//                        "   2. Grant of Copyright License. Subject to the terms and conditions of\n" +
//                        "      this License, each Contributor hereby grants to You a perpetual,\n" +
//                        "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
//                        "      copyright license to reproduce, prepare Derivative Works of,\n" +
//                        "      publicly display, publicly perform, sublicense, and distribute the\n" +
//                        "      Work and such Derivative Works in Source or Object form.\n" +
//                        "\n" +
//                        "   3. Grant of Patent License. Subject to the terms and conditions of\n" +
//                        "      this License, each Contributor hereby grants to You a perpetual,\n" +
//                        "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
//                        "      (except as stated in this section) patent license to make, have made,\n" +
//                        "      use, offer to sell, sell, import, and otherwise transfer the Work,\n" +
//                        "      where such license applies only to those patent claims licensable\n" +
//                        "      by such Contributor that are necessarily infringed by their\n" +
//                        "      Contribution(s) alone or by combination of their Contribution(s)\n" +
//                        "      with the Work to which such Contribution(s) was submitted. If You\n" +
//                        "      institute patent litigation against any entity (including a\n" +
//                        "      cross-claim or counterclaim in a lawsuit) alleging that the Work\n" +
//                        "      or a Contribution incorporated within the Work constitutes direct\n" +
//                        "      or contributory patent infringement, then any patent licenses\n" +
//                        "      granted to You under this License for that Work shall terminate\n" +
//                        "      as of the date such litigation is filed.\n" +
//                        "\n" +
//                        "   4. Redistribution. You may reproduce and distribute copies of the\n" +
//                        "      Work or Derivative Works thereof in any medium, with or without\n" +
//                        "      modifications, and in Source or Object form, provided that You\n" +
//                        "      meet the following conditions:\n" +
//                        "\n" +
//                        "      (a) You must give any other recipients of the Work or\n" +
//                        "          Derivative Works a copy of this License; and\n" +
//                        "\n" +
//                        "      (b) You must cause any modified files to carry prominent notices\n" +
//                        "          stating that You changed the files; and\n" +
//                        "\n" +
//                        "      (c) You must retain, in the Source form of any Derivative Works\n" +
//                        "          that You distribute, all copyright, patent, trademark, and\n" +
//                        "          attribution notices from the Source form of the Work,\n" +
//                        "          excluding those notices that do not pertain to any part of\n" +
//                        "          the Derivative Works; and\n" +
//                        "\n" +
//                        "      (d) If the Work includes a \"NOTICE\" text file as part of its\n" +
//                        "          distribution, then any Derivative Works that You distribute must\n" +
//                        "          include a readable copy of the attribution notices contained\n" +
//                        "          within such NOTICE file, excluding those notices that do not\n" +
//                        "          pertain to any part of the Derivative Works, in at least one\n" +
//                        "          of the following places: within a NOTICE text file distributed\n" +
//                        "          as part of the Derivative Works; within the Source form or\n" +
//                        "          documentation, if provided along with the Derivative Works; or,\n" +
//                        "          within a display generated by the Derivative Works, if and\n" +
//                        "          wherever such third-party notices normally appear. The contents\n" +
//                        "          of the NOTICE file are for informational purposes only and\n" +
//                        "          do not modify the License. You may add Your own attribution\n" +
//                        "          notices within Derivative Works that You distribute, alongside\n" +
//                        "          or as an addendum to the NOTICE text from the Work, provided\n" +
//                        "          that such additional attribution notices cannot be construed\n" +
//                        "          as modifying the License.\n" +
//                        "\n" +
//                        "      You may add Your own copyright statement to Your modifications and\n" +
//                        "      may provide additional or different license terms and conditions\n" +
//                        "      for use, reproduction, or distribution of Your modifications, or\n" +
//                        "      for any such Derivative Works as a whole, provided Your use,\n" +
//                        "      reproduction, and distribution of the Work otherwise complies with\n" +
//                        "      the conditions stated in this License.\n" +
//                        "\n" +
//                        "   5. Submission of Contributions. Unless You explicitly state otherwise,\n" +
//                        "      any Contribution intentionally submitted for inclusion in the Work\n" +
//                        "      by You to the Licensor shall be under the terms and conditions of\n" +
//                        "      this License, without any additional terms or conditions.\n" +
//                        "      Notwithstanding the above, nothing herein shall supersede or modify\n" +
//                        "      the terms of any separate license agreement you may have executed\n" +
//                        "      with Licensor regarding such Contributions.\n" +
//                        "\n" +
//                        "   6. Trademarks. This License does not grant permission to use the trade\n" +
//                        "      names, trademarks, service marks, or product names of the Licensor,\n" +
//                        "      except as required for reasonable and customary use in describing the\n" +
//                        "      origin of the Work and reproducing the content of the NOTICE file.\n" +
//                        "\n" +
//                        "   7. Disclaimer of Warranty. Unless required by applicable law or\n" +
//                        "      agreed to in writing, Licensor provides the Work (and each\n" +
//                        "      Contributor provides its Contributions) on an \"AS IS\" BASIS,\n" +
//                        "      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or\n" +
//                        "      implied, including, without limitation, any warranties or conditions\n" +
//                        "      of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A\n" +
//                        "      PARTICULAR PURPOSE. You are solely responsible for determining the\n" +
//                        "      appropriateness of using or redistributing the Work and assume any\n" +
//                        "      risks associated with Your exercise of permissions under this License.\n" +
//                        "\n" +
//                        "   8. Limitation of Liability. In no event and under no legal theory,\n" +
//                        "      whether in tort (including negligence), contract, or otherwise,\n" +
//                        "      unless required by applicable law (such as deliberate and grossly\n" +
//                        "      negligent acts) or agreed to in writing, shall any Contributor be\n" +
//                        "      liable to You for damages, including any direct, indirect, special,\n" +
//                        "      incidental, or consequential damages of any character arising as a\n" +
//                        "      result of this License or out of the use or inability to use the\n" +
//                        "      Work (including but not limited to damages for loss of goodwill,\n" +
//                        "      work stoppage, computer failure or malfunction, or any and all\n" +
//                        "      other commercial damages or losses), even if such Contributor\n" +
//                        "      has been advised of the possibility of such damages.\n" +
//                        "\n" +
//                        "   9. Accepting Warranty or Additional Liability. While redistributing\n" +
//                        "      the Work or Derivative Works thereof, You may choose to offer,\n" +
//                        "      and charge a fee for, acceptance of support, warranty, indemnity,\n" +
//                        "      or other liability obligations and/or rights consistent with this\n" +
//                        "      License. However, in accepting such obligations, You may act only\n" +
//                        "      on Your own behalf and on Your sole responsibility, not on behalf\n" +
//                        "      of any other Contributor, and only if You agree to indemnify,\n" +
//                        "      defend, and hold each Contributor harmless for any liability\n" +
//                        "      incurred by, or claims asserted against, such Contributor by reason\n" +
//                        "      of your accepting any such warranty or additional liability.\n" +
//                        "\n" +
//                        "   END OF TERMS AND CONDITIONS",
//                link = "https://developer.android.com/license"
//            ),
//            LicenseDescription(
//                id = 1,
//                License = "MIT License",
//                Description = "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
//                        "of this software and associated documentation files (the \"Software\"), to deal\n" +
//                        "in the Software without restriction, including without limitation the rights\n" +
//                        "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
//                        "copies of the Software, and to permit persons to whom the Software is\n" +
//                        "furnished to do so, subject to the following conditions:\n" +
//                        "\n" +
//                        "The above copyright notice and this permission notice shall be included in\n" +
//                        "all copies or substantial portions of the Software.\n" +
//                        "\n" +
//                        "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
//                        "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
//                        "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
//                        "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
//                        "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
//                        "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n" +
//                        "THE SOFTWARE.",
//                link = "https://opensource.org/licenses/MIT"
//            ),
//
//            LicenseDescription(
//                id = 2,
//                License = "GNU LESSER GENERAL PUBLIC LICENSE",
//                Description = "Version 2.1, February 1999\n" +
//                        "\n" +
//                        " Copyright (C) 1991, 1999 Free Software Foundation, Inc.\n" +
//                        "     59 Temple Place, Suite 330, Boston, MA  02111-1307  USA\n" +
//                        " Everyone is permitted to copy and distribute verbatim copies\n" +
//                        " of this license document, but changing it is not allowed.\n" +
//                        "\n" +
//                        "[This is the first released version of the Lesser GPL.  It also counts\n" +
//                        " as the successor of the GNU Library Public License, version 2, hence\n" +
//                        " the version number 2.1.]\n" +
//                        "\n" +
//                        "                Preamble\n" +
//                        "\n" +
//                        "  The licenses for most software are designed to take away your\n" +
//                        "freedom to share and change it.  By contrast, the GNU General Public\n" +
//                        "Licenses are intended to guarantee your freedom to share and change\n" +
//                        "free software--to make sure the software is free for all its users.\n" +
//                        "\n" +
//                        "  This license, the Lesser General Public License, applies to some\n" +
//                        "specially designated software packages--typically libraries--of the\n" +
//                        "Free Software Foundation and other authors who decide to use it.  You\n" +
//                        "can use it too, but we suggest you first think carefully about whether\n" +
//                        "this license or the ordinary General Public License is the better\n" +
//                        "strategy to use in any particular case, based on the explanations below.\n" +
//                        "\n" +
//                        "  When we speak of free software, we are referring to freedom of use,\n" +
//                        "not price.  Our General Public Licenses are designed to make sure that\n" +
//                        "you have the freedom to distribute copies of free software (and charge\n" +
//                        "for this service if you wish); that you receive source code or can get\n" +
//                        "it if you want it; that you can change the software and use pieces of\n" +
//                        "it in new free programs; and that you are informed that you can do\n" +
//                        "these things.\n" +
//                        "\n" +
//                        "  To protect your rights, we need to make restrictions that forbid\n" +
//                        "distributors to deny you these rights or to ask you to surrender these\n" +
//                        "rights.  These restrictions translate to certain responsibilities for\n" +
//                        "you if you distribute copies of the library or if you modify it.\n" +
//                        "\n" +
//                        "  For example, if you distribute copies of the library, whether gratis\n" +
//                        "or for a fee, you must give the recipients all the rights that we gave\n" +
//                        "you.  You must make sure that they, too, receive or can get the source\n" +
//                        "code.  If you link other code with the library, you must provide\n" +
//                        "complete object files to the recipients, so that they can relink them\n" +
//                        "with the library after making changes to the library and recompiling\n" +
//                        "it.  And you must show them these terms so they know their rights.\n" +
//                        "\n" +
//                        "  We protect your rights with a two-step method: (1) we copyright the\n" +
//                        "library, and (2) we offer you this license, which gives you legal\n" +
//                        "permission to copy, distribute and/or modify the library.\n" +
//                        "\n" +
//                        "  To protect each distributor, we want to make it very clear that\n" +
//                        "there is no warranty for the free library.  Also, if the library is\n" +
//                        "modified by someone else and passed on, the recipients should know\n" +
//                        "that what they have is not the original version, so that the original\n" +
//                        "author's reputation will not be affected by problems that might be\n" +
//                        "introduced by others.\n" +
//                        "\n" +
//                        "  Finally, software patents pose a constant threat to the existence of\n" +
//                        "any free program.  We wish to make sure that a company cannot\n" +
//                        "effectively restrict the users of a free program by obtaining a\n" +
//                        "restrictive license from a patent holder.  Therefore, we insist that\n" +
//                        "any patent license obtained for a version of the library must be\n" +
//                        "consistent with the full freedom of use specified in this license.\n" +
//                        "\n" +
//                        "  Most GNU software, including some libraries, is covered by the\n" +
//                        "ordinary GNU General Public License.  This license, the GNU Lesser\n" +
//                        "General Public License, applies to certain designated libraries, and\n" +
//                        "is quite different from the ordinary General Public License.  We use\n" +
//                        "this license for certain libraries in order to permit linking those\n" +
//                        "libraries into non-free programs.\n" +
//                        "\n" +
//                        "  When a program is linked with a library, whether statically or using\n" +
//                        "a shared library, the combination of the two is legally speaking a\n" +
//                        "combined work, a derivative of the original library.  The ordinary\n" +
//                        "General Public License therefore permits such linking only if the\n" +
//                        "entire combination fits its criteria of freedom.  The Lesser General\n" +
//                        "Public License permits more lax criteria for linking other code with\n" +
//                        "the library.\n" +
//                        "\n" +
//                        "  We call this license the \"Lesser\" General Public License because it\n" +
//                        "does Less to protect the user's freedom than the ordinary General\n" +
//                        "Public License.  It also provides other free software developers Less\n" +
//                        "of an advantage over competing non-free programs.  These disadvantages\n" +
//                        "are the reason we use the ordinary General Public License for many\n" +
//                        "libraries.  However, the Lesser license provides advantages in certain\n" +
//                        "special circumstances.\n" +
//                        "\n" +
//                        "  For example, on rare occasions, there may be a special need to\n" +
//                        "encourage the widest possible use of a certain library, so that it becomes\n" +
//                        "a de-facto standard.  To achieve this, non-free programs must be\n" +
//                        "allowed to use the library.  A more frequent case is that a free\n" +
//                        "library does the same job as widely used non-free libraries.  In this\n" +
//                        "case, there is little to gain by limiting the free library to free\n" +
//                        "software only, so we use the Lesser General Public License.\n" +
//                        "\n" +
//                        "  In other cases, permission to use a particular library in non-free\n" +
//                        "programs enables a greater number of people to use a large body of\n" +
//                        "free software.  For example, permission to use the GNU C Library in\n" +
//                        "non-free programs enables many more people to use the whole GNU\n" +
//                        "operating system, as well as its variant, the GNU/Linux operating\n" +
//                        "system.\n" +
//                        "\n" +
//                        "  Although the Lesser General Public License is Less protective of the\n" +
//                        "users' freedom, it does ensure that the user of a program that is\n" +
//                        "linked with the Library has the freedom and the wherewithal to run\n" +
//                        "that program using a modified version of the Library.\n" +
//                        "\n" +
//                        "  The precise terms and conditions for copying, distribution and\n" +
//                        "modification follow.  Pay close attention to the difference between a\n" +
//                        "\"work based on the library\" and a \"work that uses the library\".  The\n" +
//                        "former contains code derived from the library, whereas the latter must\n" +
//                        "be combined with the library in order to run.\n" +
//                        "\n" +
//                        "          GNU LESSER GENERAL PUBLIC LICENSE\n" +
//                        "   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION\n" +
//                        "\n" +
//                        "  0. This License Agreement applies to any software library or other\n" +
//                        "program which contains a notice placed by the copyright holder or\n" +
//                        "other authorized party saying it may be distributed under the terms of\n" +
//                        "this Lesser General Public License (also called \"this License\").\n" +
//                        "Each licensee is addressed as \"you\".\n" +
//                        "\n" +
//                        "  A \"library\" means a collection of software functions and/or data\n" +
//                        "prepared so as to be conveniently linked with application programs\n" +
//                        "(which use some of those functions and data) to form executables.\n" +
//                        "\n" +
//                        "  The \"Library\", below, refers to any such software library or work\n" +
//                        "which has been distributed under these terms.  A \"work based on the\n" +
//                        "Library\" means either the Library or any derivative work under\n" +
//                        "copyright law: that is to say, a work containing the Library or a\n" +
//                        "portion of it, either verbatim or with modifications and/or translated\n" +
//                        "straightforwardly into another language.  (Hereinafter, translation is\n" +
//                        "included without limitation in the term \"modification\".)\n" +
//                        "\n" +
//                        "  \"Source code\" for a work means the preferred form of the work for\n" +
//                        "making modifications to it.  For a library, complete source code means\n" +
//                        "all the source code for all modules it contains, plus any associated\n" +
//                        "interface definition files, plus the scripts used to control compilation\n" +
//                        "and installation of the library.\n" +
//                        "\n" +
//                        "  Activities other than copying, distribution and modification are not\n" +
//                        "covered by this License; they are outside its scope.  The act of\n" +
//                        "running a program using the Library is not restricted, and output from\n" +
//                        "such a program is covered only if its contents constitute a work based\n" +
//                        "on the Library (independent of the use of the Library in a tool for\n" +
//                        "writing it).  Whether that is true depends on what the Library does\n" +
//                        "and what the program that uses the Library does.\n" +
//                        "\n" +
//                        "  1. You may copy and distribute verbatim copies of the Library's\n" +
//                        "complete source code as you receive it, in any medium, provided that\n" +
//                        "you conspicuously and appropriately publish on each copy an\n" +
//                        "appropriate copyright notice and disclaimer of warranty; keep intact\n" +
//                        "all the notices that refer to this License and to the absence of any\n" +
//                        "warranty; and distribute a copy of this License along with the\n" +
//                        "Library.\n" +
//                        "\n" +
//                        "  You may charge a fee for the physical act of transferring a copy,\n" +
//                        "and you may at your option offer warranty protection in exchange for a\n" +
//                        "fee.\n" +
//                        "\n" +
//                        "  2. You may modify your copy or copies of the Library or any portion\n" +
//                        "of it, thus forming a work based on the Library, and copy and\n" +
//                        "distribute such modifications or work under the terms of Section 1\n" +
//                        "above, provided that you also meet all of these conditions:\n" +
//                        "\n" +
//                        "    a) The modified work must itself be a software library.\n" +
//                        "\n" +
//                        "    b) You must cause the files modified to carry prominent notices\n" +
//                        "    stating that you changed the files and the date of any change.\n" +
//                        "\n" +
//                        "    c) You must cause the whole of the work to be licensed at no\n" +
//                        "    charge to all third parties under the terms of this License.\n" +
//                        "\n" +
//                        "    d) If a facility in the modified Library refers to a function or a\n" +
//                        "    table of data to be supplied by an application program that uses\n" +
//                        "    the facility, other than as an argument passed when the facility\n" +
//                        "    is invoked, then you must make a good faith effort to ensure that,\n" +
//                        "    in the event an application does not supply such function or\n" +
//                        "    table, the facility still operates, and performs whatever part of\n" +
//                        "    its purpose remains meaningful.\n" +
//                        "\n" +
//                        "    (For example, a function in a library to compute square roots has\n" +
//                        "    a purpose that is entirely well-defined independent of the\n" +
//                        "    application.  Therefore, Subsection 2d requires that any\n" +
//                        "    application-supplied function or table used by this function must\n" +
//                        "    be optional: if the application does not supply it, the square\n" +
//                        "    root function must still compute square roots.)\n" +
//                        "\n" +
//                        "These requirements apply to the modified work as a whole.  If\n" +
//                        "identifiable sections of that work are not derived from the Library,\n" +
//                        "and can be reasonably considered independent and separate works in\n" +
//                        "themselves, then this License, and its terms, do not apply to those\n" +
//                        "sections when you distribute them as separate works.  But when you\n" +
//                        "distribute the same sections as part of a whole which is a work based\n" +
//                        "on the Library, the distribution of the whole must be on the terms of\n" +
//                        "this License, whose permissions for other licensees extend to the\n" +
//                        "entire whole, and thus to each and every part regardless of who wrote\n" +
//                        "it.\n" +
//                        "\n" +
//                        "Thus, it is not the intent of this section to claim rights or contest\n" +
//                        "your rights to work written entirely by you; rather, the intent is to\n" +
//                        "exercise the right to control the distribution of derivative or\n" +
//                        "collective works based on the Library.\n" +
//                        "\n" +
//                        "In addition, mere aggregation of another work not based on the Library\n" +
//                        "with the Library (or with a work based on the Library) on a volume of\n" +
//                        "a storage or distribution medium does not bring the other work under\n" +
//                        "the scope of this License.\n" +
//                        "\n" +
//                        "  3. You may opt to apply the terms of the ordinary GNU General Public\n" +
//                        "License instead of this License to a given copy of the Library.  To do\n" +
//                        "this, you must alter all the notices that refer to this License, so\n" +
//                        "that they refer to the ordinary GNU General Public License, version 2,\n" +
//                        "instead of to this License.  (If a newer version than version 2 of the\n" +
//                        "ordinary GNU General Public License has appeared, then you can specify\n" +
//                        "that version instead if you wish.)  Do not make any other change in\n" +
//                        "these notices.\n" +
//                        "\n" +
//                        "  Once this change is made in a given copy, it is irreversible for\n" +
//                        "that copy, so the ordinary GNU General Public License applies to all\n" +
//                        "subsequent copies and derivative works made from that copy.\n" +
//                        "\n" +
//                        "  This option is useful when you wish to copy part of the code of\n" +
//                        "the Library into a program that is not a library.\n" +
//                        "\n" +
//                        "  4. You may copy and distribute the Library (or a portion or\n" +
//                        "derivative of it, under Section 2) in object code or executable form\n" +
//                        "under the terms of Sections 1 and 2 above provided that you accompany\n" +
//                        "it with the complete corresponding machine-readable source code, which\n" +
//                        "must be distributed under the terms of Sections 1 and 2 above on a\n" +
//                        "medium customarily used for software interchange.\n" +
//                        "\n" +
//                        "  If distribution of object code is made by offering access to copy\n" +
//                        "from a designated place, then offering equivalent access to copy the\n" +
//                        "source code from the same place satisfies the requirement to\n" +
//                        "distribute the source code, even though third parties are not\n" +
//                        "compelled to copy the source along with the object code.\n" +
//                        "\n" +
//                        "  5. A program that contains no derivative of any portion of the\n" +
//                        "Library, but is designed to work with the Library by being compiled or\n" +
//                        "linked with it, is called a \"work that uses the Library\".  Such a\n" +
//                        "work, in isolation, is not a derivative work of the Library, and\n" +
//                        "therefore falls outside the scope of this License.\n" +
//                        "\n" +
//                        "  However, linking a \"work that uses the Library\" with the Library\n" +
//                        "creates an executable that is a derivative of the Library (because it\n" +
//                        "contains portions of the Library), rather than a \"work that uses the\n" +
//                        "library\".  The executable is therefore covered by this License.\n" +
//                        "Section 6 states terms for distribution of such executables.\n" +
//                        "\n" +
//                        "  When a \"work that uses the Library\" uses material from a header file\n" +
//                        "that is part of the Library, the object code for the work may be a\n" +
//                        "derivative work of the Library even though the source code is not.\n" +
//                        "Whether this is true is especially significant if the work can be\n" +
//                        "linked without the Library, or if the work is itself a library.  The\n" +
//                        "threshold for this to be true is not precisely defined by law.\n" +
//                        "\n" +
//                        "  If such an object file uses only numerical parameters, data\n" +
//                        "structure layouts and accessors, and small macros and small inline\n" +
//                        "functions (ten lines or less in length), then the use of the object\n" +
//                        "file is unrestricted, regardless of whether it is legally a derivative\n" +
//                        "work.  (Executables containing this object code plus portions of the\n" +
//                        "Library will still fall under Section 6.)\n" +
//                        "\n" +
//                        "  Otherwise, if the work is a derivative of the Library, you may\n" +
//                        "distribute the object code for the work under the terms of Section 6.\n" +
//                        "Any executables containing that work also fall under Section 6,\n" +
//                        "whether or not they are linked directly with the Library itself.\n" +
//                        "\n" +
//                        "  6. As an exception to the Sections above, you may also combine or\n" +
//                        "link a \"work that uses the Library\" with the Library to produce a\n" +
//                        "work containing portions of the Library, and distribute that work\n" +
//                        "under terms of your choice, provided that the terms permit\n" +
//                        "modification of the work for the customer's own use and reverse\n" +
//                        "engineering for debugging such modifications.\n" +
//                        "\n" +
//                        "  You must give prominent notice with each copy of the work that the\n" +
//                        "Library is used in it and that the Library and its use are covered by\n" +
//                        "this License.  You must supply a copy of this License.  If the work\n" +
//                        "during execution displays copyright notices, you must include the\n" +
//                        "copyright notice for the Library among them, as well as a reference\n" +
//                        "directing the user to the copy of this License.  Also, you must do one\n" +
//                        "of these things:\n" +
//                        "\n" +
//                        "    a) Accompany the work with the complete corresponding\n" +
//                        "    machine-readable source code for the Library including whatever\n" +
//                        "    changes were used in the work (which must be distributed under\n" +
//                        "    Sections 1 and 2 above); and, if the work is an executable linked\n" +
//                        "    with the Library, with the complete machine-readable \"work that\n" +
//                        "    uses the Library\", as object code and/or source code, so that the\n" +
//                        "    user can modify the Library and then relink to produce a modified\n" +
//                        "    executable containing the modified Library.  (It is understood\n" +
//                        "    that the user who changes the contents of definitions files in the\n" +
//                        "    Library will not necessarily be able to recompile the application\n" +
//                        "    to use the modified definitions.)\n" +
//                        "\n" +
//                        "    b) Use a suitable shared library mechanism for linking with the\n" +
//                        "    Library.  A suitable mechanism is one that (1) uses at run time a\n" +
//                        "    copy of the library already present on the user's computer system,\n" +
//                        "    rather than copying library functions into the executable, and (2)\n" +
//                        "    will operate properly with a modified version of the library, if\n" +
//                        "    the user installs one, as long as the modified version is\n" +
//                        "    interface-compatible with the version that the work was made with.\n" +
//                        "\n" +
//                        "    c) Accompany the work with a written offer, valid for at\n" +
//                        "    least three years, to give the same user the materials\n" +
//                        "    specified in Subsection 6a, above, for a charge no more\n" +
//                        "    than the cost of performing this distribution.\n" +
//                        "\n" +
//                        "    d) If distribution of the work is made by offering access to copy\n" +
//                        "    from a designated place, offer equivalent access to copy the above\n" +
//                        "    specified materials from the same place.\n" +
//                        "\n" +
//                        "    e) Verify that the user has already received a copy of these\n" +
//                        "    materials or that you have already sent this user a copy.\n" +
//                        "\n" +
//                        "  For an executable, the required form of the \"work that uses the\n" +
//                        "Library\" must include any data and utility programs needed for\n" +
//                        "reproducing the executable from it.  However, as a special exception,\n" +
//                        "the materials to be distributed need not include anything that is\n" +
//                        "normally distributed (in either source or binary form) with the major\n" +
//                        "components (compiler, kernel, and so on) of the operating system on\n" +
//                        "which the executable runs, unless that component itself accompanies\n" +
//                        "the executable.\n" +
//                        "\n" +
//                        "  It may happen that this requirement contradicts the license\n" +
//                        "restrictions of other proprietary libraries that do not normally\n" +
//                        "accompany the operating system.  Such a contradiction means you cannot\n" +
//                        "use both them and the Library together in an executable that you\n" +
//                        "distribute.\n" +
//                        "\n" +
//                        "  7. You may place library facilities that are a work based on the\n" +
//                        "Library side-by-side in a single library together with other library\n" +
//                        "facilities not covered by this License, and distribute such a combined\n" +
//                        "library, provided that the separate distribution of the work based on\n" +
//                        "the Library and of the other library facilities is otherwise\n" +
//                        "permitted, and provided that you do these two things:\n" +
//                        "\n" +
//                        "    a) Accompany the combined library with a copy of the same work\n" +
//                        "    based on the Library, uncombined with any other library\n" +
//                        "    facilities.  This must be distributed under the terms of the\n" +
//                        "    Sections above.\n" +
//                        "\n" +
//                        "    b) Give prominent notice with the combined library of the fact\n" +
//                        "    that part of it is a work based on the Library, and explaining\n" +
//                        "    where to find the accompanying uncombined form of the same work.\n" +
//                        "\n" +
//                        "  8. You may not copy, modify, sublicense, link with, or distribute\n" +
//                        "the Library except as expressly provided under this License.  Any\n" +
//                        "attempt otherwise to copy, modify, sublicense, link with, or\n" +
//                        "distribute the Library is void, and will automatically terminate your\n" +
//                        "rights under this License.  However, parties who have received copies,\n" +
//                        "or rights, from you under this License will not have their licenses\n" +
//                        "terminated so long as such parties remain in full compliance.\n" +
//                        "\n" +
//                        "  9. You are not required to accept this License, since you have not\n" +
//                        "signed it.  However, nothing else grants you permission to modify or\n" +
//                        "distribute the Library or its derivative works.  These actions are\n" +
//                        "prohibited by law if you do not accept this License.  Therefore, by\n" +
//                        "modifying or distributing the Library (or any work based on the\n" +
//                        "Library), you indicate your acceptance of this License to do so, and\n" +
//                        "all its terms and conditions for copying, distributing or modifying\n" +
//                        "the Library or works based on it.\n" +
//                        "\n" +
//                        "  10. Each time you redistribute the Library (or any work based on the\n" +
//                        "Library), the recipient automatically receives a license from the\n" +
//                        "original licensor to copy, distribute, link with or modify the Library\n" +
//                        "subject to these terms and conditions.  You may not impose any further\n" +
//                        "restrictions on the recipients' exercise of the rights granted herein.\n" +
//                        "You are not responsible for enforcing compliance by third parties with\n" +
//                        "this License.\n" +
//                        "\n" +
//                        "  11. If, as a consequence of a court judgment or allegation of patent\n" +
//                        "infringement or for any other reason (not limited to patent issues),\n" +
//                        "conditions are imposed on you (whether by court order, agreement or\n" +
//                        "otherwise) that contradict the conditions of this License, they do not\n" +
//                        "excuse you from the conditions of this License.  If you cannot\n" +
//                        "distribute so as to satisfy simultaneously your obligations under this\n" +
//                        "License and any other pertinent obligations, then as a consequence you\n" +
//                        "may not distribute the Library at all.  For example, if a patent\n" +
//                        "license would not permit royalty-free redistribution of the Library by\n" +
//                        "all those who receive copies directly or indirectly through you, then\n" +
//                        "the only way you could satisfy both it and this License would be to\n" +
//                        "refrain entirely from distribution of the Library.\n" +
//                        "\n" +
//                        "If any portion of this section is held invalid or unenforceable under any\n" +
//                        "particular circumstance, the balance of the section is intended to apply,\n" +
//                        "and the section as a whole is intended to apply in other circumstances.\n" +
//                        "\n" +
//                        "It is not the purpose of this section to induce you to infringe any\n" +
//                        "patents or other property right claims or to contest validity of any\n" +
//                        "such claims; this section has the sole purpose of protecting the\n" +
//                        "integrity of the free software distribution system which is\n" +
//                        "implemented by public license practices.  Many people have made\n" +
//                        "generous contributions to the wide range of software distributed\n" +
//                        "through that system in reliance on consistent application of that\n" +
//                        "system; it is up to the author/donor to decide if he or she is willing\n" +
//                        "to distribute software through any other system and a licensee cannot\n" +
//                        "impose that choice.\n" +
//                        "\n" +
//                        "This section is intended to make thoroughly clear what is believed to\n" +
//                        "be a consequence of the rest of this License.\n" +
//                        "\n" +
//                        "  12. If the distribution and/or use of the Library is restricted in\n" +
//                        "certain countries either by patents or by copyrighted interfaces, the\n" +
//                        "original copyright holder who places the Library under this License may add\n" +
//                        "an explicit geographical distribution limitation excluding those countries,\n" +
//                        "so that distribution is permitted only in or among countries not thus\n" +
//                        "excluded.  In such case, this License incorporates the limitation as if\n" +
//                        "written in the body of this License.\n" +
//                        "\n" +
//                        "  13. The Free Software Foundation may publish revised and/or new\n" +
//                        "versions of the Lesser General Public License from time to time.\n" +
//                        "Such new versions will be similar in spirit to the present version,\n" +
//                        "but may differ in detail to address new problems or concerns.\n" +
//                        "\n" +
//                        "Each version is given a distinguishing version number.  If the Library\n" +
//                        "specifies a version number of this License which applies to it and\n" +
//                        "\"any later version\", you have the option of following the terms and\n" +
//                        "conditions either of that version or of any later version published by\n" +
//                        "the Free Software Foundation.  If the Library does not specify a\n" +
//                        "license version number, you may choose any version ever published by\n" +
//                        "the Free Software Foundation.\n" +
//                        "\n" +
//                        "  14. If you wish to incorporate parts of the Library into other free\n" +
//                        "programs whose distribution conditions are incompatible with these,\n" +
//                        "write to the author to ask for permission.  For software which is\n" +
//                        "copyrighted by the Free Software Foundation, write to the Free\n" +
//                        "Software Foundation; we sometimes make exceptions for this.  Our\n" +
//                        "decision will be guided by the two goals of preserving the free status\n" +
//                        "of all derivatives of our free software and of promoting the sharing\n" +
//                        "and reuse of software generally.\n" +
//                        "\n" +
//                        "                NO WARRANTY\n" +
//                        "\n" +
//                        "  15. BECAUSE THE LIBRARY IS LICENSED FREE OF CHARGE, THERE IS NO\n" +
//                        "WARRANTY FOR THE LIBRARY, TO THE EXTENT PERMITTED BY APPLICABLE LAW.\n" +
//                        "EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR\n" +
//                        "OTHER PARTIES PROVIDE THE LIBRARY \"AS IS\" WITHOUT WARRANTY OF ANY\n" +
//                        "KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE\n" +
//                        "IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR\n" +
//                        "PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE\n" +
//                        "LIBRARY IS WITH YOU.  SHOULD THE LIBRARY PROVE DEFECTIVE, YOU ASSUME\n" +
//                        "THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.\n" +
//                        "\n" +
//                        "  16. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN\n" +
//                        "WRITING WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY\n" +
//                        "AND/OR REDISTRIBUTE THE LIBRARY AS PERMITTED ABOVE, BE LIABLE TO YOU\n" +
//                        "FOR DAMAGES, INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR\n" +
//                        "CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR INABILITY TO USE THE\n" +
//                        "LIBRARY (INCLUDING BUT NOT LIMITED TO LOSS OF DATA OR DATA BEING\n" +
//                        "RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD PARTIES OR A\n" +
//                        "FAILURE OF THE LIBRARY TO OPERATE WITH ANY OTHER SOFTWARE), EVEN IF\n" +
//                        "SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH\n" +
//                        "DAMAGES.\n" +
//                        "\n" +
//                        "             END OF TERMS AND CONDITIONS",
//                link = "https://www.eclipse.org/legal/epl-2.0/"
//            ),
//            LicenseDescription(
//                id = 3,
//                License = "Netscape Public License 1.1",
//                Description = "The Netscape Public License Version 1.1 (\"NPL\") consists of the\n" +
//                        "Mozilla Public License Version 1.1 with the following Amendments,\n" +
//                        "including Exhibit A-Netscape Public License.  Files identified with\n" +
//                        "\"Exhibit A-Netscape Public License\" are governed by the Netscape\n" +
//                        "Public License Version 1.1.\n" +
//                        "\n" +
//                        "Additional Terms applicable to the Netscape Public License.\n" +
//                        "\n" +
//                        "    I. Effect.\n" +
//                        "\n" +
//                        "       These additional terms described in this Netscape Public\n" +
//                        "       License -- Amendments shall apply to the Mozilla Communicator\n" +
//                        "       client code and to all Covered Code under this License.\n" +
//                        "\n" +
//                        "   II. ''Netscape's Branded Code'' means Covered Code that Netscape\n" +
//                        "       distributes and/or permits others to distribute under one or\n" +
//                        "       more trademark(s) which are controlled by Netscape but which\n" +
//                        "       are not licensed for use under this License.\n" +
//                        "\n" +
//                        "  III. Netscape and logo.\n" +
//                        "\n" +
//                        "       This License does not grant any rights to use the trademarks\n" +
//                        "       \"Netscape'', the \"Netscape N and horizon'' logo or the\n" +
//                        "       \"Netscape lighthouse\" logo, \"Netcenter\", \"Gecko\", \"Java\" or\n" +
//                        "       \"JavaScript\", \"Smart Browsing\" even if such marks are included\n" +
//                        "       in the Original Code or Modifications.\n" +
//                        "\n" +
//                        "   IV. Inability to Comply Due to Contractual Obligation.\n" +
//                        "\n" +
//                        "       Prior to licensing the Original Code under this License,\n" +
//                        "       Netscape has licensed third party code for use in Netscape's\n" +
//                        "       Branded Code. To the extent that Netscape is limited\n" +
//                        "       contractually from making such third party code available under\n" +
//                        "       this License, Netscape may choose to reintegrate such code into\n" +
//                        "       Covered Code without being required to distribute such code in\n" +
//                        "       Source Code form, even if such code would otherwise be\n" +
//                        "       considered ''Modifications'' under this License.\n" +
//                        "\n" +
//                        "    V. Use of Modifications and Covered Code by Initial Developer.\n" +
//                        "\n" +
//                        "        V.1. In General.\n" +
//                        "\n" +
//                        "             The obligations of Section 3 apply to Netscape, except to\n" +
//                        "             the extent specified in this Amendment, Section V.2 and\n" +
//                        "             V.3.\n" +
//                        "\n" +
//                        "        V.2. Other Products.\n" +
//                        "\n" +
//                        "             Netscape may include Covered Code in products other than\n" +
//                        "             the Netscape's Branded Code which are released by\n" +
//                        "             Netscape during the two (2) years following the release\n" +
//                        "             date of the Original Code, without such additional\n" +
//                        "             products becoming subject to the terms of this License,\n" +
//                        "             and may license such additional products on different\n" +
//                        "             terms from those contained in this License.\n" +
//                        "\n" +
//                        "        V.3. Alternative Licensing.\n" +
//                        "\n" +
//                        "             Netscape may license the Source Code of Netscape's\n" +
//                        "             Branded Code, including Modifications incorporated\n" +
//                        "             therein, without such Netscape Branded Code becoming\n" +
//                        "             subject to the terms of this License, and may license\n" +
//                        "             such Netscape Branded Code on different terms from those\n" +
//                        "             contained in this License.\n" +
//                        "\n" +
//                        "    VI. Litigation.\n" +
//                        "\n" +
//                        "        Notwithstanding the limitations of Section 11 above, the\n" +
//                        "        provisions regarding litigation in Section 11(a), (b) and (c)\n" +
//                        "        of the License shall apply to all disputes relating to this\n" +
//                        "        License.\n" +
//                        "\n" +
//                        "\n" +
//                        "EXHIBIT A-Netscape Public License.\n" +
//                        "\n" +
//                        "\n" +
//                        "    ''The contents of this file are subject to the Netscape Public\n" +
//                        "    License Version 1.1 (the \"License\"); you may not use this file\n" +
//                        "    except in compliance with the License. You may obtain a copy of\n" +
//                        "    the License at http://www.mozilla.org/NPL/\n" +
//                        "\n" +
//                        "    Software distributed under the License is distributed on an \"AS\n" +
//                        "    IS\" basis, WITHOUT WARRANTY OF ANY KIND, either express or\n" +
//                        "    implied. See the License for the specific language governing\n" +
//                        "    rights and limitations under the License.\n" +
//                        "\n" +
//                        "    The Original Code is Mozilla Communicator client code, released\n" +
//                        "    March 31, 1998.\n" +
//                        "\n" +
//                        "    The Initial Developer of the Original Code is Netscape\n" +
//                        "    Communications Corporation. Portions created by Netscape are\n" +
//                        "    Copyright (C) 1998-1999 Netscape Communications Corporation. All\n" +
//                        "    Rights Reserved.\n" +
//                        "\n" +
//                        "    Contributor(s): ______________________________________.\n" +
//                        "\n" +
//                        "\n" +
//                        "    Alternatively, the contents of this file may be used under the\n" +
//                        "    terms of the _____ license (the �[___] License�), in which case\n" +
//                        "    the provisions of [______] License are applicable instead of those\n" +
//                        "    above.  If you wish to allow use of your version of this file only\n" +
//                        "    under the terms of the [____] License and not to allow others to\n" +
//                        "    use your version of this file under the NPL, indicate your\n" +
//                        "    decision by deleting the provisions above and replace them with\n" +
//                        "    the notice and other provisions required by the [___] License.  If\n" +
//                        "    you do not delete the provisions above, a recipient may use your\n" +
//                        "    version of this file under either the NPL or the [___] License.\"",
//                link = "https://opensource.org/licenses/NPL-1.1"
//            ),
//            LicenseDescription(
//                id = 4,
//                License = "MOZILLA PUBLIC LICENSE Version 1.1",
//                Description = "1. Definitions.\n" +
//                        "\n" +
//                        "    1.0.1. \"Commercial Use\" means distribution or otherwise making the\n" +
//                        "    Covered Code available to a third party.\n" +
//                        "\n" +
//                        "    1.1. ''Contributor'' means each entity that creates or contributes\n" +
//                        "    to the creation of Modifications.\n" +
//                        "\n" +
//                        "    1.2. ''Contributor Version'' means the combination of the Original\n" +
//                        "    Code, prior Modifications used by a Contributor, and the\n" +
//                        "    Modifications made by that particular Contributor.\n" +
//                        "\n" +
//                        "    1.3. ''Covered Code'' means the Original Code or Modifications or\n" +
//                        "    the combination of the Original Code and Modifications, in each\n" +
//                        "    case including portions thereof.\n" +
//                        "\n" +
//                        "    1.4. ''Electronic Distribution Mechanism'' means a mechanism\n" +
//                        "    generally accepted in the software development community for the\n" +
//                        "    electronic transfer of data.\n" +
//                        "\n" +
//                        "    1.5. ''Executable'' means Covered Code in any form other than Source Code.\n" +
//                        "\n" +
//                        "    1.6. ''Initial Developer'' means the individual or entity\n" +
//                        "    identified as the Initial Developer in the Source Code notice\n" +
//                        "    required by Exhibit A.\n" +
//                        "\n" +
//                        "    1.7. ''Larger Work'' means a work which combines Covered Code or\n" +
//                        "    portions thereof with code not governed by the terms of this\n" +
//                        "    License.\n" +
//                        "\n" +
//                        "    1.8. ''License'' means this document.\n" +
//                        "\n" +
//                        "    1.8.1. \"Licensable\" means having the right to grant, to the\n" +
//                        "    maximum extent possible, whether at the time of the initial grant\n" +
//                        "    or subsequently acquired, any and all of the rights conveyed\n" +
//                        "    herein.\n" +
//                        "\n" +
//                        "    1.9. ''Modifications'' means any addition to or deletion from the\n" +
//                        "    substance or structure of either the Original Code or any previous\n" +
//                        "    Modifications. When Covered Code is released as a series of files,\n" +
//                        "    a Modification is:\n" +
//                        "\n" +
//                        "        A. Any addition to or deletion from the contents of a file\n" +
//                        "        containing Original Code or previous Modifications.\n" +
//                        "\n" +
//                        "        B. Any new file that contains any part of the Original Code or\n" +
//                        "        previous Modifications.\n" +
//                        "\n" +
//                        "    1.10. ''Original Code'' means Source Code of computer software\n" +
//                        "    code which is described in the Source Code notice required by\n" +
//                        "    Exhibit A as Original Code, and which, at the time of its release\n" +
//                        "    under this License is not already Covered Code governed by this\n" +
//                        "    License.\n" +
//                        "\n" +
//                        "    1.10.1. \"Patent Claims\" means any patent claim(s), now owned or\n" +
//                        "    hereafter acquired, including without limitation, method, process,\n" +
//                        "    and apparatus claims, in any patent Licensable by grantor.\n" +
//                        "\n" +
//                        "    1.11. ''Source Code'' means the preferred form of the Covered Code\n" +
//                        "    for making modifications to it, including all modules it contains,\n" +
//                        "    plus any associated interface definition files, scripts used to\n" +
//                        "    control compilation and installation of an Executable, or source\n" +
//                        "    code differential comparisons against either the Original Code or\n" +
//                        "    another well known, available Covered Code of the Contributor's\n" +
//                        "    choice. The Source Code can be in a compressed or archival form,\n" +
//                        "    provided the appropriate decompression or de-archiving software is\n" +
//                        "    widely available for no charge.\n" +
//                        "\n" +
//                        "    1.12. \"You'' (or \"Your\") means an individual or a legal entity\n" +
//                        "    exercising rights under, and complying with all of the terms of,\n" +
//                        "    this License or a future version of this License issued under\n" +
//                        "    Section 6.1. For legal entities, \"You'' includes any entity which\n" +
//                        "    controls, is controlled by, or is under common control with\n" +
//                        "    You. For purposes of this definition, \"control'' means (a) the\n" +
//                        "    power, direct or indirect, to cause the direction or management of\n" +
//                        "    such entity, whether by contract or otherwise, or (b) ownership of\n" +
//                        "    more than fifty percent (50%) of the outstanding shares or\n" +
//                        "    beneficial ownership of such entity.\n" +
//                        "\n" +
//                        "2. Source Code License.\n" +
//                        "\n" +
//                        "    2.1. The Initial Developer Grant.\n" +
//                        "\n" +
//                        "    The Initial Developer hereby grants You a world-wide,\n" +
//                        "    royalty-free, non-exclusive license, subject to third party\n" +
//                        "    intellectual property claims:\n" +
//                        "\n" +
//                        "        (a) under intellectual property rights (other than patent or\n" +
//                        "        trademark) Licensable by Initial Developer to use, reproduce,\n" +
//                        "        modify, display, perform, sublicense and distribute the\n" +
//                        "        Original Code (or portions thereof) with or without\n" +
//                        "        Modifications, and/or as part of a Larger Work; and\n" +
//                        "\n" +
//                        "        (b) under Patents Claims infringed by the making, using or\n" +
//                        "        selling of Original Code, to make, have made, use, practice,\n" +
//                        "        sell, and offer for sale, and/or otherwise dispose of the\n" +
//                        "        Original Code (or portions thereof).\n" +
//                        "\n" +
//                        "        (c) the licenses granted in this Section 2.1(a) and (b) are\n" +
//                        "        effective on the date Initial Developer first distributes\n" +
//                        "        Original Code under the terms of this License.\n" +
//                        "\n" +
//                        "        (d) Notwithstanding Section 2.1(b) above, no patent license is\n" +
//                        "        granted: 1) for code that You delete from the Original Code;\n" +
//                        "        2) separate from the Original Code; or 3) for infringements\n" +
//                        "        caused by: i) the modification of the Original Code or ii) the\n" +
//                        "        combination of the Original Code with other software or\n" +
//                        "        devices.\n" +
//                        "\n" +
//                        "    2.2. Contributor Grant.\n" +
//                        "\n" +
//                        "    Subject to third party intellectual property claims, each\n" +
//                        "    Contributor hereby grants You a world-wide, royalty-free,\n" +
//                        "    non-exclusive license\n" +
//                        "\n" +
//                        "        (a) under intellectual property rights (other than patent or\n" +
//                        "        trademark) Licensable by Contributor, to use, reproduce,\n" +
//                        "        modify, display, perform, sublicense and distribute the\n" +
//                        "        Modifications created by such Contributor (or portions\n" +
//                        "        thereof) either on an unmodified basis, with other\n" +
//                        "        Modifications, as Covered Code and/or as part of a Larger\n" +
//                        "        Work; and\n" +
//                        "\n" +
//                        "        (b) under Patent Claims infringed by the making, using, or\n" +
//                        "        selling of Modifications made by that Contributor either alone\n" +
//                        "        and/or in combination with its Contributor Version (or\n" +
//                        "        portions of such combination), to make, use, sell, offer for\n" +
//                        "        sale, have made, and/or otherwise dispose of: 1) Modifications\n" +
//                        "        made by that Contributor (or portions thereof); and 2) the\n" +
//                        "        combination of Modifications made by that Contributor with its\n" +
//                        "        Contributor Version (or portions of such combination).\n" +
//                        "\n" +
//                        "        (c) the licenses granted in Sections 2.2(a) and 2.2(b) are\n" +
//                        "        effective on the date Contributor first makes Commercial Use\n" +
//                        "        of the Covered Code.\n" +
//                        "\n" +
//                        "        (d) Notwithstanding Section 2.2(b) above, no patent license is\n" +
//                        "        granted: 1) for any code that Contributor has deleted from the\n" +
//                        "        Contributor Version; 2) separate from the Contributor Version;\n" +
//                        "        3) for infringements caused by: i) third party modifications\n" +
//                        "        of Contributor Version or ii) the combination of Modifications\n" +
//                        "        made by that Contributor with other software (except as part\n" +
//                        "        of the Contributor Version) or other devices; or 4) under\n" +
//                        "        Patent Claims infringed by Covered Code in the absence of\n" +
//                        "        Modifications made by that Contributor.\n" +
//                        "\n" +
//                        "\n" +
//                        "3. Distribution Obligations.\n" +
//                        "\n" +
//                        "    3.1. Application of License.\n" +
//                        "\n" +
//                        "    The Modifications which You create or to which You contribute are\n" +
//                        "    governed by the terms of this License, including without\n" +
//                        "    limitation Section 2.2. The Source Code version of Covered Code\n" +
//                        "    may be distributed only under the terms of this License or a\n" +
//                        "    future version of this License released under Section 6.1, and You\n" +
//                        "    must include a copy of this License with every copy of the Source\n" +
//                        "    Code You distribute. You may not offer or impose any terms on any\n" +
//                        "    Source Code version that alters or restricts the applicable\n" +
//                        "    version of this License or the recipients' rights\n" +
//                        "    hereunder. However, You may include an additional document\n" +
//                        "    offering the additional rights described in Section 3.5.\n" +
//                        "\n" +
//                        "    3.2. Availability of Source Code.\n" +
//                        "\n" +
//                        "    Any Modification which You create or to which You contribute must\n" +
//                        "    be made available in Source Code form under the terms of this\n" +
//                        "    License either on the same media as an Executable version or via\n" +
//                        "    an accepted Electronic Distribution Mechanism to anyone to whom\n" +
//                        "    you made an Executable version available; and if made available\n" +
//                        "    via Electronic Distribution Mechanism, must remain available for\n" +
//                        "    at least twelve (12) months after the date it initially became\n" +
//                        "    available, or at least six (6) months after a subsequent version\n" +
//                        "    of that particular Modification has been made available to such\n" +
//                        "    recipients. You are responsible for ensuring that the Source Code\n" +
//                        "    version remains available even if the Electronic Distribution\n" +
//                        "    Mechanism is maintained by a third party.\n" +
//                        "\n" +
//                        "    3.3. Description of Modifications.\n" +
//                        "\n" +
//                        "    You must cause all Covered Code to which You contribute to contain\n" +
//                        "    a file documenting the changes You made to create that Covered\n" +
//                        "    Code and the date of any change. You must include a prominent\n" +
//                        "    statement that the Modification is derived, directly or\n" +
//                        "    indirectly, from Original Code provided by the Initial Developer\n" +
//                        "    and including the name of the Initial Developer in (a) the Source\n" +
//                        "    Code, and (b) in any notice in an Executable version or related\n" +
//                        "    documentation in which You describe the origin or ownership of the\n" +
//                        "    Covered Code.\n" +
//                        "\n" +
//                        "    3.4. Intellectual Property Matters\n" +
//                        "\n" +
//                        "        (a) Third Party Claims.\n" +
//                        "\n" +
//                        "        If Contributor has knowledge that a license under a third\n" +
//                        "        party's intellectual property rights is required to exercise\n" +
//                        "        the rights granted by such Contributor under Sections 2.1 or\n" +
//                        "        2.2, Contributor must include a text file with the Source Code\n" +
//                        "        distribution titled \"LEGAL'' which describes the claim and the\n" +
//                        "        party making the claim in sufficient detail that a recipient\n" +
//                        "        will know whom to contact. If Contributor obtains such\n" +
//                        "        knowledge after the Modification is made available as\n" +
//                        "        described in Section 3.2, Contributor shall promptly modify\n" +
//                        "        the LEGAL file in all copies Contributor makes available\n" +
//                        "        thereafter and shall take other steps (such as notifying\n" +
//                        "        appropriate mailing lists or newsgroups) reasonably calculated\n" +
//                        "        to inform those who received the Covered Code that new\n" +
//                        "        knowledge has been obtained.\n" +
//                        "\n" +
//                        "        (b) Contributor APIs.\n" +
//                        "\n" +
//                        "        If Contributor's Modifications include an application\n" +
//                        "        programming interface and Contributor has knowledge of patent\n" +
//                        "        licenses which are reasonably necessary to implement that API,\n" +
//                        "        Contributor must also include this information in the LEGAL\n" +
//                        "        file.\n" +
//                        "\n" +
//                        "        (c)    Representations.\n" +
//                        "\n" +
//                        "        Contributor represents that, except as disclosed pursuant to\n" +
//                        "        Section 3.4(a) above, Contributor believes that Contributor's\n" +
//                        "        Modifications are Contributor's original creation(s) and/or\n" +
//                        "        Contributor has sufficient rights to grant the rights conveyed\n" +
//                        "        by this License.\n" +
//                        "\n" +
//                        "\n" +
//                        "    3.5. Required Notices.\n" +
//                        "\n" +
//                        "    You must duplicate the notice in Exhibit A in each file of the\n" +
//                        "    Source Code.  If it is not possible to put such notice in a\n" +
//                        "    particular Source Code file due to its structure, then You must\n" +
//                        "    include such notice in a location (such as a relevant directory)\n" +
//                        "    where a user would be likely to look for such a notice.  If You\n" +
//                        "    created one or more Modification(s) You may add your name as a\n" +
//                        "    Contributor to the notice described in Exhibit A.  You must also\n" +
//                        "    duplicate this License in any documentation for the Source Code\n" +
//                        "    where You describe recipients' rights or ownership rights relating\n" +
//                        "    to Covered Code.  You may choose to offer, and to charge a fee\n" +
//                        "    for, warranty, support, indemnity or liability obligations to one\n" +
//                        "    or more recipients of Covered Code. However, You may do so only on\n" +
//                        "    Your own behalf, and not on behalf of the Initial Developer or any\n" +
//                        "    Contributor. You must make it absolutely clear than any such\n" +
//                        "    warranty, support, indemnity or liability obligation is offered by\n" +
//                        "    You alone, and You hereby agree to indemnify the Initial Developer\n" +
//                        "    and every Contributor for any liability incurred by the Initial\n" +
//                        "    Developer or such Contributor as a result of warranty, support,\n" +
//                        "    indemnity or liability terms You offer.\n" +
//                        "\n" +
//                        "    3.6. Distribution of Executable Versions.\n" +
//                        "\n" +
//                        "    You may distribute Covered Code in Executable form only if the\n" +
//                        "    requirements of Section 3.1-3.5 have been met for that Covered\n" +
//                        "    Code, and if You include a notice stating that the Source Code\n" +
//                        "    version of the Covered Code is available under the terms of this\n" +
//                        "    License, including a description of how and where You have\n" +
//                        "    fulfilled the obligations of Section 3.2. The notice must be\n" +
//                        "    conspicuously included in any notice in an Executable version,\n" +
//                        "    related documentation or collateral in which You describe\n" +
//                        "    recipients' rights relating to the Covered Code. You may\n" +
//                        "    distribute the Executable version of Covered Code or ownership\n" +
//                        "    rights under a license of Your choice, which may contain terms\n" +
//                        "    different from this License, provided that You are in compliance\n" +
//                        "    with the terms of this License and that the license for the\n" +
//                        "    Executable version does not attempt to limit or alter the\n" +
//                        "    recipient's rights in the Source Code version from the rights set\n" +
//                        "    forth in this License. If You distribute the Executable version\n" +
//                        "    under a different license You must make it absolutely clear that\n" +
//                        "    any terms which differ from this License are offered by You alone,\n" +
//                        "    not by the Initial Developer or any Contributor. You hereby agree\n" +
//                        "    to indemnify the Initial Developer and every Contributor for any\n" +
//                        "    liability incurred by the Initial Developer or such Contributor as\n" +
//                        "    a result of any such terms You offer.\n" +
//                        "\n" +
//                        "    3.7. Larger Works.\n" +
//                        "\n" +
//                        "    You may create a Larger Work by combining Covered Code with other\n" +
//                        "    code not governed by the terms of this License and distribute the\n" +
//                        "    Larger Work as a single product. In such a case, You must make\n" +
//                        "    sure the requirements of this License are fulfilled for the\n" +
//                        "    Covered Code.\n" +
//                        "\n" +
//                        "4. Inability to Comply Due to Statute or Regulation.\n" +
//                        "\n" +
//                        "    If it is impossible for You to comply with any of the terms of\n" +
//                        "    this License with respect to some or all of the Covered Code due\n" +
//                        "    to statute, judicial order, or regulation then You must: (a)\n" +
//                        "    comply with the terms of this License to the maximum extent\n" +
//                        "    possible; and (b) describe the limitations and the code they\n" +
//                        "    affect. Such description must be included in the LEGAL file\n" +
//                        "    described in Section 3.4 and must be included with all\n" +
//                        "    distributions of the Source Code. Except to the extent prohibited\n" +
//                        "    by statute or regulation, such description must be sufficiently\n" +
//                        "    detailed for a recipient of ordinary skill to be able to\n" +
//                        "    understand it.\n" +
//                        "\n" +
//                        "5. Application of this License.\n" +
//                        "\n" +
//                        "    This License applies to code to which the Initial Developer has\n" +
//                        "    attached the notice in Exhibit A and to related Covered Code.\n" +
//                        "\n" +
//                        "6. Versions of the License.\n" +
//                        "\n" +
//                        "    6.1. New Versions.\n" +
//                        "\n" +
//                        "    Netscape Communications Corporation (''Netscape'') may publish\n" +
//                        "    revised and/or new versions of the License from time to time. Each\n" +
//                        "    version will be given a distinguishing version number.\n" +
//                        "\n" +
//                        "    6.2. Effect of New Versions.\n" +
//                        "\n" +
//                        "    Once Covered Code has been published under a particular version of\n" +
//                        "    the License, You may always continue to use it under the terms of\n" +
//                        "    that version. You may also choose to use such Covered Code under\n" +
//                        "    the terms of any subsequent version of the License published by\n" +
//                        "    Netscape. No one other than Netscape has the right to modify the\n" +
//                        "    terms applicable to Covered Code created under this License.\n" +
//                        "\n" +
//                        "    6.3. Derivative Works.\n" +
//                        "\n" +
//                        "    If You create or use a modified version of this License (which you\n" +
//                        "    may only do in order to apply it to code which is not already\n" +
//                        "    Covered Code governed by this License), You must (a) rename Your\n" +
//                        "    license so that the phrases ''Mozilla'', ''MOZILLAPL'', ''MOZPL'',\n" +
//                        "    ''Netscape'', \"MPL\", ''NPL'' or any confusingly similar phrase do\n" +
//                        "    not appear in your license (except to note that your license\n" +
//                        "    differs from this License) and (b) otherwise make it clear that\n" +
//                        "    Your version of the license contains terms which differ from the\n" +
//                        "    Mozilla Public License and Netscape Public License. (Filling in\n" +
//                        "    the name of the Initial Developer, Original Code or Contributor in\n" +
//                        "    the notice described in Exhibit A shall not of themselves be\n" +
//                        "    deemed to be modifications of this License.)\n" +
//                        "\n" +
//                        "7. DISCLAIMER OF WARRANTY.\n" +
//                        "\n" +
//                        "    COVERED CODE IS PROVIDED UNDER THIS LICENSE ON AN \"AS IS'' BASIS,\n" +
//                        "    WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED,\n" +
//                        "    INCLUDING, WITHOUT LIMITATION, WARRANTIES THAT THE COVERED CODE IS\n" +
//                        "    FREE OF DEFECTS, MERCHANTABLE, FIT FOR A PARTICULAR PURPOSE OR\n" +
//                        "    NON-INFRINGING. THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE\n" +
//                        "    OF THE COVERED CODE IS WITH YOU. SHOULD ANY COVERED CODE PROVE\n" +
//                        "    DEFECTIVE IN ANY RESPECT, YOU (NOT THE INITIAL DEVELOPER OR ANY\n" +
//                        "    OTHER CONTRIBUTOR) ASSUME THE COST OF ANY NECESSARY SERVICING,\n" +
//                        "    REPAIR OR CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN\n" +
//                        "    ESSENTIAL PART OF THIS LICENSE. NO USE OF ANY COVERED CODE IS\n" +
//                        "    AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.\n" +
//                        "\n" +
//                        "8. TERMINATION.\n" +
//                        "\n" +
//                        "    8.1.  This License and the rights granted hereunder will terminate\n" +
//                        "    automatically if You fail to comply with terms herein and fail to\n" +
//                        "    cure such breach within 30 days of becoming aware of the\n" +
//                        "    breach. All sublicenses to the Covered Code which are properly\n" +
//                        "    granted shall survive any termination of this License. Provisions\n" +
//                        "    which, by their nature, must remain in effect beyond the\n" +
//                        "    termination of this License shall survive.\n" +
//                        "\n" +
//                        "    8.2.  If You initiate litigation by asserting a patent\n" +
//                        "    infringement claim (excluding declatory judgment actions) against\n" +
//                        "    Initial Developer or a Contributor (the Initial Developer or\n" +
//                        "    Contributor against whom You file such action is referred to as\n" +
//                        "    \"Participant\") alleging that:\n" +
//                        "\n" +
//                        "    (a) such Participant's Contributor Version directly or indirectly\n" +
//                        "    infringes any patent, then any and all rights granted by such\n" +
//                        "    Participant to You under Sections 2.1 and/or 2.2 of this License\n" +
//                        "    shall, upon 60 days notice from Participant terminate\n" +
//                        "    prospectively, unless if within 60 days after receipt of notice\n" +
//                        "    You either: (i) agree in writing to pay Participant a mutually\n" +
//                        "    agreeable reasonable royalty for Your past and future use of\n" +
//                        "    Modifications made by such Participant, or (ii) withdraw Your\n" +
//                        "    litigation claim with respect to the Contributor Version against\n" +
//                        "    such Participant.  If within 60 days of notice, a reasonable\n" +
//                        "    royalty and payment arrangement are not mutually agreed upon in\n" +
//                        "    writing by the parties or the litigation claim is not withdrawn,\n" +
//                        "    the rights granted by Participant to You under Sections 2.1 and/or\n" +
//                        "    2.2 automatically terminate at the expiration of the 60 day notice\n" +
//                        "    period specified above.\n" +
//                        "\n" +
//                        "    (b) any software, hardware, or device, other than such\n" +
//                        "    Participant's Contributor Version, directly or indirectly\n" +
//                        "    infringes any patent, then any rights granted to You by such\n" +
//                        "    Participant under Sections 2.1(b) and 2.2(b) are revoked effective\n" +
//                        "    as of the date You first made, used, sold, distributed, or had\n" +
//                        "    made, Modifications made by that Participant.\n" +
//                        "\n" +
//                        "    8.3.  If You assert a patent infringement claim against\n" +
//                        "    Participant alleging that such Participant's Contributor Version\n" +
//                        "    directly or indirectly infringes any patent where such claim is\n" +
//                        "    resolved (such as by license or settlement) prior to the\n" +
//                        "    initiation of patent infringement litigation, then the reasonable\n" +
//                        "    value of the licenses granted by such Participant under Sections\n" +
//                        "    2.1 or 2.2 shall be taken into account in determining the amount\n" +
//                        "    or value of any payment or license.\n" +
//                        "\n" +
//                        "    8.4.  In the event of termination under Sections 8.1 or 8.2 above,\n" +
//                        "    all end user license agreements (excluding distributors and\n" +
//                        "    resellers) which have been validly granted by You or any\n" +
//                        "    distributor hereunder prior to termination shall survive\n" +
//                        "    termination.\n" +
//                        "\n" +
//                        "9. LIMITATION OF LIABILITY.\n" +
//                        "\n" +
//                        "    UNDER NO CIRCUMSTANCES AND UNDER NO LEGAL THEORY, WHETHER TORT\n" +
//                        "    (INCLUDING NEGLIGENCE), CONTRACT, OR OTHERWISE, SHALL YOU, THE\n" +
//                        "    INITIAL DEVELOPER, ANY OTHER CONTRIBUTOR, OR ANY DISTRIBUTOR OF\n" +
//                        "    COVERED CODE, OR ANY SUPPLIER OF ANY OF SUCH PARTIES, BE LIABLE TO\n" +
//                        "    ANY PERSON FOR ANY INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL\n" +
//                        "    DAMAGES OF ANY CHARACTER INCLUDING, WITHOUT LIMITATION, DAMAGES\n" +
//                        "    FOR LOSS OF GOODWILL, WORK STOPPAGE, COMPUTER FAILURE OR\n" +
//                        "    MALFUNCTION, OR ANY AND ALL OTHER COMMERCIAL DAMAGES OR LOSSES,\n" +
//                        "    EVEN IF SUCH PARTY SHALL HAVE BEEN INFORMED OF THE POSSIBILITY OF\n" +
//                        "    SUCH DAMAGES. THIS LIMITATION OF LIABILITY SHALL NOT APPLY TO\n" +
//                        "    LIABILITY FOR DEATH OR PERSONAL INJURY RESULTING FROM SUCH PARTY'S\n" +
//                        "    NEGLIGENCE TO THE EXTENT APPLICABLE LAW PROHIBITS SUCH\n" +
//                        "    LIMITATION. SOME JURISDICTIONS DO NOT ALLOW THE EXCLUSION OR\n" +
//                        "    LIMITATION OF INCIDENTAL OR CONSEQUENTIAL DAMAGES, SO THIS\n" +
//                        "    EXCLUSION AND LIMITATION MAY NOT APPLY TO YOU.\n" +
//                        "\n" +
//                        "10. U.S. GOVERNMENT END USERS.\n" +
//                        "\n" +
//                        "    The Covered Code is a ''commercial item,'' as that term is defined\n" +
//                        "    in 48 C.F.R. 2.101 (Oct. 1995), consisting of ''commercial\n" +
//                        "    computer software'' and ''commercial computer software\n" +
//                        "    documentation,'' as such terms are used in 48 C.F.R. 12.212\n" +
//                        "    (Sept. 1995). Consistent with 48 C.F.R. 12.212 and 48\n" +
//                        "    C.F.R. 227.7202-1 through 227.7202-4 (June 1995), all\n" +
//                        "    U.S. Government End Users acquire Covered Code with only those\n" +
//                        "    rights set forth herein.\n" +
//                        "\n" +
//                        "11. MISCELLANEOUS.\n" +
//                        "\n" +
//                        "    This License represents the complete agreement concerning subject\n" +
//                        "    matter hereof. If any provision of this License is held to be\n" +
//                        "    unenforceable, such provision shall be reformed only to the extent\n" +
//                        "    necessary to make it enforceable. This License shall be governed\n" +
//                        "    by California law provisions (except to the extent applicable law,\n" +
//                        "    if any, provides otherwise), excluding its conflict-of-law\n" +
//                        "    provisions. With respect to disputes in which at least one party\n" +
//                        "    is a citizen of, or an entity chartered or registered to do\n" +
//                        "    business in the United States of America, any litigation relating\n" +
//                        "    to this License shall be subject to the jurisdiction of the\n" +
//                        "    Federal Courts of the Northern District of California, with venue\n" +
//                        "    lying in Santa Clara County, California, with the losing party\n" +
//                        "    responsible for costs, including without limitation, court costs\n" +
//                        "    and reasonable attorneys' fees and expenses. The application of\n" +
//                        "    the United Nations Convention on Contracts for the International\n" +
//                        "    Sale of Goods is expressly excluded. Any law or regulation which\n" +
//                        "    provides that the language of a contract shall be construed\n" +
//                        "    against the drafter shall not apply to this License.\n" +
//                        "\n" +
//                        "12. RESPONSIBILITY FOR CLAIMS.\n" +
//                        "\n" +
//                        "    As between Initial Developer and the Contributors, each party is\n" +
//                        "    responsible for claims and damages arising, directly or\n" +
//                        "    indirectly, out of its utilization of rights under this License\n" +
//                        "    and You agree to work with Initial Developer and Contributors to\n" +
//                        "    distribute such responsibility on an equitable basis. Nothing\n" +
//                        "    herein is intended or shall be deemed to constitute any admission\n" +
//                        "    of liability.\n" +
//                        "\n" +
//                        "13. MULTIPLE-LICENSED CODE.\n" +
//                        "\n" +
//                        "    Initial Developer may designate portions of the Covered Code as\n" +
//                        "    �Multiple-Licensed�.  �Multiple-Licensed� means that the Initial\n" +
//                        "    Developer permits you to utilize portions of the Covered Code\n" +
//                        "    under Your choice of the NPL or the alternative licenses, if any,\n" +
//                        "    specified by the Initial Developer in the file described in\n" +
//                        "    Exhibit A.\n" +
//                        "\n" +
//                        "\n" +
//                        "EXHIBIT A -Mozilla Public License.\n" +
//                        "\n" +
//                        "    ``The contents of this file are subject to the Mozilla Public\n" +
//                        "    License Version 1.1 (the \"License\"); you may not use this file\n" +
//                        "    except in compliance with the License. You may obtain a copy of\n" +
//                        "    the License at http://www.mozilla.org/MPL/\n" +
//                        "\n" +
//                        "    Software distributed under the License is distributed on an \"AS\n" +
//                        "    IS\" basis, WITHOUT WARRANTY OF ANY KIND, either express or\n" +
//                        "    implied. See the License for the specific language governing\n" +
//                        "    rights and limitations under the License.\n" +
//                        "\n" +
//                        "    The Original Code is ______________________________________.\n" +
//                        "\n" +
//                        "    The Initial Developer of the Original Code is\n" +
//                        "     ________________________. Portions created by\n" +
//                        "     ______________________ are Copyright (C) ______\n" +
//                        "     _______________________. All Rights Reserved.\n" +
//                        "\n" +
//                        "    Contributor(s): ______________________________________.\n" +
//                        "\n" +
//                        "    Alternatively, the contents of this file may be used under the\n" +
//                        "    terms of the _____ license (the �[___] License�), in which case\n" +
//                        "    the provisions of [______] License are applicable instead of those\n" +
//                        "    above.  If you wish to allow use of your version of this file only\n" +
//                        "    under the terms of the [____] License and not to allow others to\n" +
//                        "    use your version of this file under the MPL, indicate your\n" +
//                        "    decision by deleting the provisions above and replace them with\n" +
//                        "    the notice and other provisions required by the [___] License.  If\n" +
//                        "    you do not delete the provisions above, a recipient may use your\n" +
//                        "    version of this file under either the MPL or the [___] License.\"\n" +
//                        "\n" +
//                        "    [NOTE: The text of this Exhibit A may differ slightly from the\n" +
//                        "    text of the notices in the Source Code files of the Original\n" +
//                        "    Code. You should use the text of this Exhibit A rather than the\n" +
//                        "    text found in the Original Code Source Code for Your\n" +
//                        "    Modifications.]",
//                link = "https://www.mozilla.org/MPL/1.1/"
//            ),
//            LicenseDescription(
//                id = 5,
//                License = "GNU GENERAL PUBLIC LICENSE",
//                Description = "Version 2, June 1991\n" +
//                        "\n" +
//                        "   Copyright (C) 1989, 1991 Free Software Foundation, Inc.,\n" +
//                        "   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA\n" +
//                        "   Everyone is permitted to copy and distribute verbatim copies\n" +
//                        "   of this license document, but changing it is not allowed.\n" +
//                        "\n" +
//                        "\t    Preamble\n" +
//                        "\n" +
//                        "    The licenses for most software are designed to take away your\n" +
//                        "  freedom to share and change it.  By contrast, the GNU General Public\n" +
//                        "  License is intended to guarantee your freedom to share and change free\n" +
//                        "  software--to make sure the software is free for all its users.  This\n" +
//                        "  General Public License applies to most of the Free Software\n" +
//                        "  Foundation's software and to any other program whose authors commit to\n" +
//                        "  using it.  (Some other Free Software Foundation software is covered by\n" +
//                        "  the GNU Lesser General Public License instead.)  You can apply it to\n" +
//                        "  your programs, too.\n" +
//                        "\n" +
//                        "    When we speak of free software, we are referring to freedom, not\n" +
//                        "  price.  Our General Public Licenses are designed to make sure that you\n" +
//                        "  have the freedom to distribute copies of free software (and charge for\n" +
//                        "  this service if you wish), that you receive source code or can get it\n" +
//                        "  if you want it, that you can change the software or use pieces of it\n" +
//                        "  in new free programs; and that you know you can do these things.\n" +
//                        "\n" +
//                        "    To protect your rights, we need to make restrictions that forbid\n" +
//                        "  anyone to deny you these rights or to ask you to surrender the rights.\n" +
//                        "  These restrictions translate to certain responsibilities for you if you\n" +
//                        "  distribute copies of the software, or if you modify it.\n" +
//                        "\n" +
//                        "    For example, if you distribute copies of such a program, whether\n" +
//                        "  gratis or for a fee, you must give the recipients all the rights that\n" +
//                        "  you have.  You must make sure that they, too, receive or can get the\n" +
//                        "  source code.  And you must show them these terms so they know their\n" +
//                        "  rights.\n" +
//                        "\n" +
//                        "    We protect your rights with two steps: (1) copyright the software, and\n" +
//                        "  (2) offer you this license which gives you legal permission to copy,\n" +
//                        "  distribute and/or modify the software.\n" +
//                        "\n" +
//                        "    Also, for each author's protection and ours, we want to make certain\n" +
//                        "  that everyone understands that there is no warranty for this free\n" +
//                        "  software.  If the software is modified by someone else and passed on, we\n" +
//                        "  want its recipients to know that what they have is not the original, so\n" +
//                        "  that any problems introduced by others will not reflect on the original\n" +
//                        "  authors' reputations.\n" +
//                        "\n" +
//                        "    Finally, any free program is threatened constantly by software\n" +
//                        "  patents.  We wish to avoid the danger that redistributors of a free\n" +
//                        "  program will individually obtain patent licenses, in effect making the\n" +
//                        "  program proprietary.  To prevent this, we have made it clear that any\n" +
//                        "  patent must be licensed for everyone's free use or not licensed at all.\n" +
//                        "\n" +
//                        "    The precise terms and conditions for copying, distribution and\n" +
//                        "  modification follow.\n" +
//                        "\n" +
//                        "\t  GNU GENERAL PUBLIC LICENSE\n" +
//                        "     TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION\n" +
//                        "\n" +
//                        "    0. This License applies to any program or other work which contains\n" +
//                        "  a notice placed by the copyright holder saying it may be distributed\n" +
//                        "  under the terms of this General Public License.  The \"Program\", below,\n" +
//                        "  refers to any such program or work, and a \"work based on the Program\"\n" +
//                        "  means either the Program or any derivative work under copyright law:\n" +
//                        "  that is to say, a work containing the Program or a portion of it,\n" +
//                        "  either verbatim or with modifications and/or translated into another\n" +
//                        "  language.  (Hereinafter, translation is included without limitation in\n" +
//                        "  the term \"modification\".)  Each licensee is addressed as \"you\".\n" +
//                        "\n" +
//                        "  Activities other than copying, distribution and modification are not\n" +
//                        "  covered by this License; they are outside its scope.  The act of\n" +
//                        "  running the Program is not restricted, and the output from the Program\n" +
//                        "  is covered only if its contents constitute a work based on the\n" +
//                        "  Program (independent of having been made by running the Program).\n" +
//                        "  Whether that is true depends on what the Program does.\n" +
//                        "\n" +
//                        "    1. You may copy and distribute verbatim copies of the Program's\n" +
//                        "  source code as you receive it, in any medium, provided that you\n" +
//                        "  conspicuously and appropriately publish on each copy an appropriate\n" +
//                        "  copyright notice and disclaimer of warranty; keep intact all the\n" +
//                        "  notices that refer to this License and to the absence of any warranty;\n" +
//                        "  and give any other recipients of the Program a copy of this License\n" +
//                        "  along with the Program.\n" +
//                        "\n" +
//                        "  You may charge a fee for the physical act of transferring a copy, and\n" +
//                        "  you may at your option offer warranty protection in exchange for a fee.\n" +
//                        "\n" +
//                        "    2. You may modify your copy or copies of the Program or any portion\n" +
//                        "  of it, thus forming a work based on the Program, and copy and\n" +
//                        "  distribute such modifications or work under the terms of Section 1\n" +
//                        "  above, provided that you also meet all of these conditions:\n" +
//                        "\n" +
//                        "      a) You must cause the modified files to carry prominent notices\n" +
//                        "      stating that you changed the files and the date of any change.\n" +
//                        "\n" +
//                        "      b) You must cause any work that you distribute or publish, that in\n" +
//                        "      whole or in part contains or is derived from the Program or any\n" +
//                        "      part thereof, to be licensed as a whole at no charge to all third\n" +
//                        "      parties under the terms of this License.\n" +
//                        "\n" +
//                        "      c) If the modified program normally reads commands interactively\n" +
//                        "      when run, you must cause it, when started running for such\n" +
//                        "      interactive use in the most ordinary way, to print or display an\n" +
//                        "      announcement including an appropriate copyright notice and a\n" +
//                        "      notice that there is no warranty (or else, saying that you provide\n" +
//                        "      a warranty) and that users may redistribute the program under\n" +
//                        "      these conditions, and telling the user how to view a copy of this\n" +
//                        "      License.  (Exception: if the Program itself is interactive but\n" +
//                        "      does not normally print such an announcement, your work based on\n" +
//                        "      the Program is not required to print an announcement.)\n" +
//                        "\n" +
//                        "  These requirements apply to the modified work as a whole.  If\n" +
//                        "  identifiable sections of that work are not derived from the Program,\n" +
//                        "  and can be reasonably considered independent and separate works in\n" +
//                        "  themselves, then this License, and its terms, do not apply to those\n" +
//                        "  sections when you distribute them as separate works.  But when you\n" +
//                        "  distribute the same sections as part of a whole which is a work based\n" +
//                        "  on the Program, the distribution of the whole must be on the terms of\n" +
//                        "  this License, whose permissions for other licensees extend to the\n" +
//                        "  entire whole, and thus to each and every part regardless of who wrote it.\n" +
//                        "\n" +
//                        "  Thus, it is not the intent of this section to claim rights or contest\n" +
//                        "  your rights to work written entirely by you; rather, the intent is to\n" +
//                        "  exercise the right to control the distribution of derivative or\n" +
//                        "  collective works based on the Program.\n" +
//                        "\n" +
//                        "  In addition, mere aggregation of another work not based on the Program\n" +
//                        "  with the Program (or with a work based on the Program) on a volume of\n" +
//                        "  a storage or distribution medium does not bring the other work under\n" +
//                        "  the scope of this License.\n" +
//                        "\n" +
//                        "    3. You may copy and distribute the Program (or a work based on it,\n" +
//                        "  under Section 2) in object code or executable form under the terms of\n" +
//                        "  Sections 1 and 2 above provided that you also do one of the following:\n" +
//                        "\n" +
//                        "      a) Accompany it with the complete corresponding machine-readable\n" +
//                        "      source code, which must be distributed under the terms of Sections\n" +
//                        "      1 and 2 above on a medium customarily used for software interchange; or,\n" +
//                        "\n" +
//                        "      b) Accompany it with a written offer, valid for at least three\n" +
//                        "      years, to give any third party, for a charge no more than your\n" +
//                        "      cost of physically performing source distribution, a complete\n" +
//                        "      machine-readable copy of the corresponding source code, to be\n" +
//                        "      distributed under the terms of Sections 1 and 2 above on a medium\n" +
//                        "      customarily used for software interchange; or,\n" +
//                        "\n" +
//                        "      c) Accompany it with the information you received as to the offer\n" +
//                        "      to distribute corresponding source code.  (This alternative is\n" +
//                        "      allowed only for noncommercial distribution and only if you\n" +
//                        "      received the program in object code or executable form with such\n" +
//                        "      an offer, in accord with Subsection b above.)\n" +
//                        "\n" +
//                        "  The source code for a work means the preferred form of the work for\n" +
//                        "  making modifications to it.  For an executable work, complete source\n" +
//                        "  code means all the source code for all modules it contains, plus any\n" +
//                        "  associated interface definition files, plus the scripts used to\n" +
//                        "  control compilation and installation of the executable.  However, as a\n" +
//                        "  special exception, the source code distributed need not include\n" +
//                        "  anything that is normally distributed (in either source or binary\n" +
//                        "  form) with the major components (compiler, kernel, and so on) of the\n" +
//                        "  operating system on which the executable runs, unless that component\n" +
//                        "  itself accompanies the executable.\n" +
//                        "\n" +
//                        "  If distribution of executable or object code is made by offering\n" +
//                        "  access to copy from a designated place, then offering equivalent\n" +
//                        "  access to copy the source code from the same place counts as\n" +
//                        "  distribution of the source code, even though third parties are not\n" +
//                        "  compelled to copy the source along with the object code.\n" +
//                        "\n" +
//                        "    4. You may not copy, modify, sublicense, or distribute the Program\n" +
//                        "  except as expressly provided under this License.  Any attempt\n" +
//                        "  otherwise to copy, modify, sublicense or distribute the Program is\n" +
//                        "  void, and will automatically terminate your rights under this License.\n" +
//                        "  However, parties who have received copies, or rights, from you under\n" +
//                        "  this License will not have their licenses terminated so long as such\n" +
//                        "  parties remain in full compliance.\n" +
//                        "\n" +
//                        "    5. You are not required to accept this License, since you have not\n" +
//                        "  signed it.  However, nothing else grants you permission to modify or\n" +
//                        "  distribute the Program or its derivative works.  These actions are\n" +
//                        "  prohibited by law if you do not accept this License.  Therefore, by\n" +
//                        "  modifying or distributing the Program (or any work based on the\n" +
//                        "  Program), you indicate your acceptance of this License to do so, and\n" +
//                        "  all its terms and conditions for copying, distributing or modifying\n" +
//                        "  the Program or works based on it.\n" +
//                        "\n" +
//                        "    6. Each time you redistribute the Program (or any work based on the\n" +
//                        "  Program), the recipient automatically receives a license from the\n" +
//                        "  original licensor to copy, distribute or modify the Program subject to\n" +
//                        "  these terms and conditions.  You may not impose any further\n" +
//                        "  restrictions on the recipients' exercise of the rights granted herein.\n" +
//                        "  You are not responsible for enforcing compliance by third parties to\n" +
//                        "  this License.\n" +
//                        "\n" +
//                        "    7. If, as a consequence of a court judgment or allegation of patent\n" +
//                        "  infringement or for any other reason (not limited to patent issues),\n" +
//                        "  conditions are imposed on you (whether by court order, agreement or\n" +
//                        "  otherwise) that contradict the conditions of this License, they do not\n" +
//                        "  excuse you from the conditions of this License.  If you cannot\n" +
//                        "  distribute so as to satisfy simultaneously your obligations under this\n" +
//                        "  License and any other pertinent obligations, then as a consequence you\n" +
//                        "  may not distribute the Program at all.  For example, if a patent\n" +
//                        "  license would not permit royalty-free redistribution of the Program by\n" +
//                        "  all those who receive copies directly or indirectly through you, then\n" +
//                        "  the only way you could satisfy both it and this License would be to\n" +
//                        "  refrain entirely from distribution of the Program.\n" +
//                        "\n" +
//                        "  If any portion of this section is held invalid or unenforceable under\n" +
//                        "  any particular circumstance, the balance of the section is intended to\n" +
//                        "  apply and the section as a whole is intended to apply in other\n" +
//                        "  circumstances.\n" +
//                        "\n" +
//                        "  It is not the purpose of this section to induce you to infringe any\n" +
//                        "  patents or other property right claims or to contest validity of any\n" +
//                        "  such claims; this section has the sole purpose of protecting the\n" +
//                        "  integrity of the free software distribution system, which is\n" +
//                        "  implemented by public license practices.  Many people have made\n" +
//                        "  generous contributions to the wide range of software distributed\n" +
//                        "  through that system in reliance on consistent application of that\n" +
//                        "  system; it is up to the author/donor to decide if he or she is willing\n" +
//                        "  to distribute software through any other system and a licensee cannot\n" +
//                        "  impose that choice.\n" +
//                        "\n" +
//                        "  This section is intended to make thoroughly clear what is believed to\n" +
//                        "  be a consequence of the rest of this License.\n" +
//                        "\n" +
//                        "    8. If the distribution and/or use of the Program is restricted in\n" +
//                        "  certain countries either by patents or by copyrighted interfaces, the\n" +
//                        "  original copyright holder who places the Program under this License\n" +
//                        "  may add an explicit geographical distribution limitation excluding\n" +
//                        "  those countries, so that distribution is permitted only in or among\n" +
//                        "  countries not thus excluded.  In such case, this License incorporates\n" +
//                        "  the limitation as if written in the body of this License.\n" +
//                        "\n" +
//                        "    9. The Free Software Foundation may publish revised and/or new versions\n" +
//                        "  of the General Public License from time to time.  Such new versions will\n" +
//                        "  be similar in spirit to the present version, but may differ in detail to\n" +
//                        "  address new problems or concerns.\n" +
//                        "\n" +
//                        "  Each version is given a distinguishing version number.  If the Program\n" +
//                        "  specifies a version number of this License which applies to it and \"any\n" +
//                        "  later version\", you have the option of following the terms and conditions\n" +
//                        "  either of that version or of any later version published by the Free\n" +
//                        "  Software Foundation.  If the Program does not specify a version number of\n" +
//                        "  this License, you may choose any version ever published by the Free Software\n" +
//                        "  Foundation.\n" +
//                        "\n" +
//                        "    10. If you wish to incorporate parts of the Program into other free\n" +
//                        "  programs whose distribution conditions are different, write to the author\n" +
//                        "  to ask for permission.  For software which is copyrighted by the Free\n" +
//                        "  Software Foundation, write to the Free Software Foundation; we sometimes\n" +
//                        "  make exceptions for this.  Our decision will be guided by the two goals\n" +
//                        "  of preserving the free status of all derivatives of our free software and\n" +
//                        "  of promoting the sharing and reuse of software generally.\n" +
//                        "\n" +
//                        "\t    NO WARRANTY\n" +
//                        "\n" +
//                        "    11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY\n" +
//                        "  FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN\n" +
//                        "  OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES\n" +
//                        "  PROVIDE THE PROGRAM \"AS IS\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED\n" +
//                        "  OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF\n" +
//                        "  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS\n" +
//                        "  TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE\n" +
//                        "  PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,\n" +
//                        "  REPAIR OR CORRECTION.\n" +
//                        "\n" +
//                        "    12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING\n" +
//                        "  WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR\n" +
//                        "  REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,\n" +
//                        "  INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING\n" +
//                        "  OUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED\n" +
//                        "  TO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY\n" +
//                        "  YOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER\n" +
//                        "  PROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE\n" +
//                        "  POSSIBILITY OF SUCH DAMAGES.\n" +
//                        "\n" +
//                        "\t   END OF TERMS AND CONDITIONS",
//                link = "https://opensource.org/licenses/MIT"
//            ),
//            LicenseDescription(
//                id = 6,
//                License = "Boost Software License",
//                Description = "Version 1.0 - August 17th, 2003\n" +
//                        "\n" +
//                        "Permission is hereby granted, free of charge, to any person or organization\n" +
//                        "obtaining a copy of the software and accompanying documentation covered by\n" +
//                        "this license (the \"Software\") to use, reproduce, display, distribute,\n" +
//                        "execute, and transmit the Software, and to prepare derivative works of the\n" +
//                        "Software, and to permit third-parties to whom the Software is furnished to\n" +
//                        "do so, all subject to the following:\n" +
//                        "\n" +
//                        "The copyright notices in the Software and this entire statement, including\n" +
//                        "the above license grant, this restriction and the following disclaimer,\n" +
//                        "must be included in all copies of the Software, in whole or in part, and\n" +
//                        "all derivative works of the Software, unless such copies or derivative\n" +
//                        "works are solely in the form of machine-executable object code generated by\n" +
//                        "a source language processor.\n" +
//                        "\n" +
//                        "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
//                        "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
//                        "FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT\n" +
//                        "SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE\n" +
//                        "FOR ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE,\n" +
//                        "ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER\n" +
//                        "DEALINGS IN THE SOFTWARE.",
//                link = "https://www.boost.org/LICENSE_1_0.txt"
//            ),
//            LicenseDescription(
//                id = 7,
//                License = "Eclipse Public License, Version 1.0 (EPL-1.0)",
//                Description = "THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC\n" +
//                        "LICENSE (\"AGREEMENT\"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM\n" +
//                        "CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.\n" +
//                        "\n" +
//                        "1. DEFINITIONS\n" +
//                        "\n" +
//                        "\"Contribution\" means:\n" +
//                        "\n" +
//                        "    a) in the case of the initial Contributor, the initial code and\n" +
//                        "       documentation distributed under this Agreement, and\n" +
//                        "\n" +
//                        "    b) in the case of each subsequent Contributor:\n" +
//                        "        i) changes to the Program, and\n" +
//                        "        ii) additions to the Program;\n" +
//                        "\n" +
//                        "where such changes and/or additions to the Program originate from and are\n" +
//                        "distributed by that particular Contributor. A Contribution 'originates'\n" +
//                        "from a Contributor if it was added to the Program by such Contributor itself\n" +
//                        "or anyone acting on such Contributor's behalf. Contributions do not include\n" +
//                        "additions to the Program which: (i) are separate modules of software\n" +
//                        "distributed in conjunction with the Program under their own license agreement,\n" +
//                        "and (ii) are not derivative works of the Program.\n" +
//                        "\n" +
//                        "\"Contributor\" means any person or entity that distributes the Program.\n" +
//                        "\n" +
//                        "\"Licensed Patents \" mean patent claims licensable by a Contributor which are\n" +
//                        "necessarily infringed by the use or sale of its Contribution alone or\n" +
//                        "when combined with the Program.\n" +
//                        "\n" +
//                        "\"Program\" means the Contributions distributed in accordance with\n" +
//                        "this Agreement.\n" +
//                        "\n" +
//                        "\"Recipient\" means anyone who receives the Program under this Agreement,\n" +
//                        "including all Contributors.\n" +
//                        "\n" +
//                        "2. GRANT OF RIGHTS\n" +
//                        "\n" +
//                        "    a) Subject to the terms of this Agreement, each Contributor hereby grants\n" +
//                        "       Recipient a non-exclusive, worldwide, royalty-free copyright license to\n" +
//                        "       reproduce, prepare derivative works of, publicly display, publicly\n" +
//                        "       perform, distribute and sublicense the Contribution of such\n" +
//                        "       Contributor, if any, and such derivative works,\n" +
//                        "       in source code and object code form.\n" +
//                        "\n" +
//                        "    b) Subject to the terms of this Agreement, each Contributor hereby grants\n" +
//                        "       Recipient a non-exclusive, worldwide, royalty-free patent license under\n" +
//                        "       Licensed Patents to make, use, sell, offer to sell, import and\n" +
//                        "       otherwise transfer the Contribution of such Contributor, if any,\n" +
//                        "       in source code and object code form. This patent license shall apply\n" +
//                        "       to the combination of the Contribution and the Program if, at the time\n" +
//                        "       the Contribution is added by the Contributor, such addition of the\n" +
//                        "       Contribution causes such combination to be covered by the\n" +
//                        "       Licensed Patents. The patent license shall not apply to any other\n" +
//                        "       combinations which include the Contribution.\n" +
//                        "       No hardware per se is licensed hereunder.\n" +
//                        "\n" +
//                        "    c) Recipient understands that although each Contributor grants the\n" +
//                        "       licenses to its Contributions set forth herein, no assurances are\n" +
//                        "       provided by any Contributor that the Program does not infringe the\n" +
//                        "       patent or other intellectual property rights of any other entity.\n" +
//                        "       Each Contributor disclaims any liability to Recipient for claims\n" +
//                        "       brought by any other entity based on infringement of intellectual\n" +
//                        "       property rights or otherwise. As a condition to exercising the\n" +
//                        "       rights and licenses granted hereunder, each Recipient hereby assumes\n" +
//                        "       sole responsibility to secure any other intellectual property rights\n" +
//                        "       needed, if any. For example, if a third party patent license is\n" +
//                        "       required to allow Recipient to distribute the Program, it is\n" +
//                        "       Recipient's responsibility to acquire that license\n" +
//                        "       before distributing the Program.\n" +
//                        "\n" +
//                        "    d) Each Contributor represents that to its knowledge it has sufficient\n" +
//                        "       copyright rights in its Contribution, if any, to grant the copyright\n" +
//                        "       license set forth in this Agreement.\n" +
//                        "\n" +
//                        "3. REQUIREMENTS\n" +
//                        "\n" +
//                        "A Contributor may choose to distribute the Program in object code form under\n" +
//                        "its own license agreement, provided that:\n" +
//                        "\n" +
//                        "    a) it complies with the terms and conditions of this Agreement; and\n" +
//                        "\n" +
//                        "    b) its license agreement:\n" +
//                        "\n" +
//                        "        i) effectively disclaims on behalf of all Contributors all warranties\n" +
//                        "        and conditions, express and implied, including warranties or\n" +
//                        "        conditions of title and non-infringement, and implied warranties or\n" +
//                        "        conditions of merchantability and fitness for a particular purpose;\n" +
//                        "\n" +
//                        "        ii) effectively excludes on behalf of all Contributors all liability\n" +
//                        "        for damages, including direct, indirect, special, incidental and\n" +
//                        "        consequential damages, such as lost profits;\n" +
//                        "\n" +
//                        "        iii) states that any provisions which differ from this Agreement are\n" +
//                        "        offered by that Contributor alone and not by any other party; and\n" +
//                        "\n" +
//                        "        iv) states that source code for the Program is available from such\n" +
//                        "        Contributor, and informs licensees how to obtain it in a reasonable\n" +
//                        "        manner on or through a medium customarily used for software exchange.\n" +
//                        "\n" +
//                        "When the Program is made available in source code form:\n" +
//                        "\n" +
//                        "    a) it must be made available under this Agreement; and\n" +
//                        "    b) a copy of this Agreement must be included with each copy of the Program.\n" +
//                        "\n" +
//                        "Contributors may not remove or alter any copyright notices contained\n" +
//                        "within the Program.\n" +
//                        "\n" +
//                        "Each Contributor must identify itself as the originator of its Contribution,\n" +
//                        "if any, in a manner that reasonably allows subsequent Recipients to\n" +
//                        "identify the originator of the Contribution.\n" +
//                        "\n" +
//                        "4. COMMERCIAL DISTRIBUTION\n" +
//                        "\n" +
//                        "Commercial distributors of software may accept certain responsibilities with\n" +
//                        "respect to end users, business partners and the like. While this license is\n" +
//                        "intended to facilitate the commercial use of the Program, the Contributor who\n" +
//                        "includes the Program in a commercial product offering should do so in a manner\n" +
//                        "which does not create potential liability for other Contributors. Therefore,\n" +
//                        "if a Contributor includes the Program in a commercial product offering,\n" +
//                        "such Contributor (\"Commercial Contributor\") hereby agrees to defend and\n" +
//                        "indemnify every other Contributor (\"Indemnified Contributor\") against any\n" +
//                        "losses, damages and costs (collectively \"Losses\") arising from claims,\n" +
//                        "lawsuits and other legal actions brought by a third party against the\n" +
//                        "Indemnified Contributor to the extent caused by the acts or omissions of\n" +
//                        "such Commercial Contributor in connection with its distribution of the Program\n" +
//                        "in a commercial product offering. The obligations in this section do not apply\n" +
//                        "to any claims or Losses relating to any actual or alleged intellectual\n" +
//                        "property infringement. In order to qualify, an Indemnified Contributor must:\n" +
//                        "a) promptly notify the Commercial Contributor in writing of such claim,\n" +
//                        "and b) allow the Commercial Contributor to control, and cooperate with the\n" +
//                        "Commercial Contributor in, the defense and any related settlement\n" +
//                        "negotiations. The Indemnified Contributor may participate in any such\n" +
//                        "claim at its own expense.\n" +
//                        "\n" +
//                        "For example, a Contributor might include the Program in a commercial product\n" +
//                        "offering, Product X. That Contributor is then a Commercial Contributor.\n" +
//                        "If that Commercial Contributor then makes performance claims, or offers\n" +
//                        "warranties related to Product X, those performance claims and warranties\n" +
//                        "are such Commercial Contributor's responsibility alone. Under this section,\n" +
//                        "the Commercial Contributor would have to defend claims against the other\n" +
//                        "Contributors related to those performance claims and warranties, and if a\n" +
//                        "court requires any other Contributor to pay any damages as a result,\n" +
//                        "the Commercial Contributor must pay those damages.\n" +
//                        "\n" +
//                        "5. NO WARRANTY\n" +
//                        "\n" +
//                        "EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, THE PROGRAM IS PROVIDED ON AN\n" +
//                        "\"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, EITHER EXPRESS OR\n" +
//                        "IMPLIED INCLUDING, WITHOUT LIMITATION, ANY WARRANTIES OR CONDITIONS OF TITLE,\n" +
//                        "NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.\n" +
//                        "Each Recipient is solely responsible for determining the appropriateness of\n" +
//                        "using and distributing the Program and assumes all risks associated with its\n" +
//                        "exercise of rights under this Agreement , including but not limited to the\n" +
//                        "risks and costs of program errors, compliance with applicable laws, damage to\n" +
//                        "or loss of data, programs or equipment, and unavailability\n" +
//                        "or interruption of operations.\n" +
//                        "\n" +
//                        "6. DISCLAIMER OF LIABILITY\n" +
//                        "\n" +
//                        "EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, NEITHER RECIPIENT NOR ANY\n" +
//                        "CONTRIBUTORS SHALL HAVE ANY LIABILITY FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n" +
//                        "SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING WITHOUT LIMITATION\n" +
//                        "LOST PROFITS), HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\n" +
//                        "CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\n" +
//                        "ARISING IN ANY WAY OUT OF THE USE OR DISTRIBUTION OF THE PROGRAM OR THE\n" +
//                        "EXERCISE OF ANY RIGHTS GRANTED HEREUNDER, EVEN IF ADVISED OF THE\n" +
//                        "POSSIBILITY OF SUCH DAMAGES.\n" +
//                        "\n" +
//                        "7. GENERAL\n" +
//                        "\n" +
//                        "If any provision of this Agreement is invalid or unenforceable under\n" +
//                        "applicable law, it shall not affect the validity or enforceability of the\n" +
//                        "remainder of the terms of this Agreement, and without further action by\n" +
//                        "the parties hereto, such provision shall be reformed to the minimum extent\n" +
//                        "necessary to make such provision valid and enforceable.\n" +
//                        "\n" +
//                        "If Recipient institutes patent litigation against any entity (including a\n" +
//                        "cross-claim or counterclaim in a lawsuit) alleging that the Program itself\n" +
//                        "(excluding combinations of the Program with other software or hardware)\n" +
//                        "infringes such Recipient's patent(s), then such Recipient's rights granted\n" +
//                        "under Section 2(b) shall terminate as of the date such litigation is filed.\n" +
//                        "\n" +
//                        "All Recipient's rights under this Agreement shall terminate if it fails to\n" +
//                        "comply with any of the material terms or conditions of this Agreement and\n" +
//                        "does not cure such failure in a reasonable period of time after becoming\n" +
//                        "aware of such noncompliance. If all Recipient's rights under this\n" +
//                        "Agreement terminate, Recipient agrees to cease use and distribution of the\n" +
//                        "Program as soon as reasonably practicable. However, Recipient's obligations\n" +
//                        "under this Agreement and any licenses granted by Recipient relating to the\n" +
//                        "Program shall continue and survive.\n" +
//                        "\n" +
//                        "Everyone is permitted to copy and distribute copies of this Agreement,\n" +
//                        "but in order to avoid inconsistency the Agreement is copyrighted and may\n" +
//                        "only be modified in the following manner. The Agreement Steward reserves\n" +
//                        "the right to publish new versions (including revisions) of this Agreement\n" +
//                        "from time to time. No one other than the Agreement Steward has the right to\n" +
//                        "modify this Agreement. The Eclipse Foundation is the initial\n" +
//                        "Agreement Steward. The Eclipse Foundation may assign the responsibility to\n" +
//                        "serve as the Agreement Steward to a suitable separate entity. Each new version\n" +
//                        "of the Agreement will be given a distinguishing version number. The Program\n" +
//                        "(including Contributions) may always be distributed subject to the version\n" +
//                        "of the Agreement under which it was received. In addition, after a new version\n" +
//                        "of the Agreement is published, Contributor may elect to distribute the Program\n" +
//                        "(including its Contributions) under the new version. Except as expressly\n" +
//                        "stated in Sections 2(a) and 2(b) above, Recipient receives no rights or\n" +
//                        "licenses to the intellectual property of any Contributor under this Agreement,\n" +
//                        "whether expressly, by implication, estoppel or otherwise. All rights in the\n" +
//                        "Program not expressly granted under this Agreement are reserved.\n" +
//                        "\n" +
//                        "This Agreement is governed by the laws of the State of New York and the\n" +
//                        "intellectual property laws of the United States of America. No party to\n" +
//                        "this Agreement will bring a legal action under this Agreement more than one\n" +
//                        "year after the cause of action arose. Each party waives its rights to a\n" +
//                        "jury trial in any resulting litigation.",
//                link = "https://www.eclipse.org/legal/epl-1.0/"
//            ),
//            LicenseDescription(
//                id = 8,
//                License = "zlib License",
//                Description = "This software is provided 'as-is', without any express or implied warranty.\n" +
//                        "In no event will the authors be held liable for any damages arising from the\n" +
//                        "use of this software.\n" +
//                        "\n" +
//                        "Permission is granted to anyone to use this software for any purpose, including\n" +
//                        "commercial applications, and to alter it and redistribute it freely, subject\n" +
//                        "to the following restrictions:\n" +
//                        "\n" +
//                        "1. The origin of this software must not be misrepresented; you must not claim\n" +
//                        "that you wrote the original software. If you use this software in a product,\n" +
//                        "an acknowledgment in the product documentation would be appreciated but is\n" +
//                        "not required.\n" +
//                        "\n" +
//                        "2. Altered source versions must be plainly marked as such, and must not be\n" +
//                        "misrepresented as being the original software.\n" +
//                        "\n" +
//                        "   3. This notice may not be removed or altered from any source distribution.\n" +
//                        "                                 Apache License\n" +
//                        "                           Version 2.0, January 2004\n" +
//                        "                        http://www.apache.org/licenses/\n" +
//                        "\n" +
//                        "   TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n" +
//                        "\n" +
//                        "   1. Definitions.\n" +
//                        "\n" +
//                        "      \"License\" shall mean the terms and conditions for use, reproduction,\n" +
//                        "      and distribution as defined by Sections 1 through 9 of this document.\n" +
//                        "\n" +
//                        "      \"Licensor\" shall mean the copyright owner or entity authorized by\n" +
//                        "      the copyright owner that is granting the License.\n" +
//                        "\n" +
//                        "      \"Legal Entity\" shall mean the union of the acting entity and all\n" +
//                        "      other entities that control, are controlled by, or are under common\n" +
//                        "      control with that entity. For the purposes of this definition,\n" +
//                        "      \"control\" means (i) the power, direct or indirect, to cause the\n" +
//                        "      direction or management of such entity, whether by contract or\n" +
//                        "      otherwise, or (ii) ownership of fifty percent (50%) or more of the\n" +
//                        "      outstanding shares, or (iii) beneficial ownership of such entity.\n" +
//                        "\n" +
//                        "      \"You\" (or \"Your\") shall mean an individual or Legal Entity\n" +
//                        "      exercising permissions granted by this License.\n" +
//                        "\n" +
//                        "      \"Source\" form shall mean the preferred form for making modifications,\n" +
//                        "      including but not limited to software source code, documentation\n" +
//                        "      source, and configuration files.\n" +
//                        "\n" +
//                        "      \"Object\" form shall mean any form resulting from mechanical\n" +
//                        "      transformation or translation of a Source form, including but\n" +
//                        "      not limited to compiled object code, generated documentation,\n" +
//                        "      and conversions to other media types.\n" +
//                        "\n" +
//                        "      \"Work\" shall mean the work of authorship, whether in Source or\n" +
//                        "      Object form, made available under the License, as indicated by a\n" +
//                        "      copyright notice that is included in or attached to the work\n" +
//                        "      (an example is provided in the Appendix below).\n" +
//                        "\n" +
//                        "      \"Derivative Works\" shall mean any work, whether in Source or Object\n" +
//                        "      form, that is based on (or derived from) the Work and for which the\n" +
//                        "      editorial revisions, annotations, elaborations, or other modifications\n" +
//                        "      represent, as a whole, an original work of authorship. For the purposes\n" +
//                        "      of this License, Derivative Works shall not include works that remain\n" +
//                        "      separable from, or merely link (or bind by name) to the interfaces of,\n" +
//                        "      the Work and Derivative Works thereof.\n" +
//                        "\n" +
//                        "      \"Contribution\" shall mean any work of authorship, including\n" +
//                        "      the original version of the Work and any modifications or additions\n" +
//                        "      to that Work or Derivative Works thereof, that is intentionally\n" +
//                        "      submitted to Licensor for inclusion in the Work by the copyright owner\n" +
//                        "      or by an individual or Legal Entity authorized to submit on behalf of\n" +
//                        "      the copyright owner. For the purposes of this definition, \"submitted\"\n" +
//                        "      means any form of electronic, verbal, or written communication sent\n" +
//                        "      to the Licensor or its representatives, including but not limited to\n" +
//                        "      communication on electronic mailing lists, source code control systems,\n" +
//                        "      and issue tracking systems that are managed by, or on behalf of, the\n" +
//                        "      Licensor for the purpose of discussing and improving the Work, but\n" +
//                        "      excluding communication that is conspicuously marked or otherwise\n" +
//                        "      designated in writing by the copyright owner as \"Not a Contribution.\"\n" +
//                        "\n" +
//                        "      \"Contributor\" shall mean Licensor and any individual or Legal Entity\n" +
//                        "      on behalf of whom a Contribution has been received by Licensor and\n" +
//                        "      subsequently incorporated within the Work.\n" +
//                        "\n" +
//                        "   2. Grant of Copyright License. Subject to the terms and conditions of\n" +
//                        "      this License, each Contributor hereby grants to You a perpetual,\n" +
//                        "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
//                        "      copyright license to reproduce, prepare Derivative Works of,\n" +
//                        "      publicly display, publicly perform, sublicense, and distribute the\n" +
//                        "      Work and such Derivative Works in Source or Object form.\n" +
//                        "\n" +
//                        "   3. Grant of Patent License. Subject to the terms and conditions of\n" +
//                        "      this License, each Contributor hereby grants to You a perpetual,\n" +
//                        "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
//                        "      (except as stated in this section) patent license to make, have made,\n" +
//                        "      use, offer to sell, sell, import, and otherwise transfer the Work,\n" +
//                        "      where such license applies only to those patent claims licensable\n" +
//                        "      by such Contributor that are necessarily infringed by their\n" +
//                        "      Contribution(s) alone or by combination of their Contribution(s)\n" +
//                        "      with the Work to which such Contribution(s) was submitted. If You\n" +
//                        "      institute patent litigation against any entity (including a\n" +
//                        "      cross-claim or counterclaim in a lawsuit) alleging that the Work\n" +
//                        "      or a Contribution incorporated within the Work constitutes direct\n" +
//                        "      or contributory patent infringement, then any patent licenses\n" +
//                        "      granted to You under this License for that Work shall terminate\n" +
//                        "      as of the date such litigation is filed.\n" +
//                        "\n" +
//                        "   4. Redistribution. You may reproduce and distribute copies of the\n" +
//                        "      Work or Derivative Works thereof in any medium, with or without\n" +
//                        "      modifications, and in Source or Object form, provided that You\n" +
//                        "      meet the following conditions:\n" +
//                        "\n" +
//                        "      (a) You must give any other recipients of the Work or\n" +
//                        "          Derivative Works a copy of this License; and\n" +
//                        "\n" +
//                        "      (b) You must cause any modified files to carry prominent notices\n" +
//                        "          stating that You changed the files; and\n" +
//                        "\n" +
//                        "      (c) You must retain, in the Source form of any Derivative Works\n" +
//                        "          that You distribute, all copyright, patent, trademark, and\n" +
//                        "          attribution notices from the Source form of the Work,\n" +
//                        "          excluding those notices that do not pertain to any part of\n" +
//                        "          the Derivative Works; and\n" +
//                        "\n" +
//                        "      (d) If the Work includes a \"NOTICE\" text file as part of its\n" +
//                        "          distribution, then any Derivative Works that You distribute must\n" +
//                        "          include a readable copy of the attribution notices contained\n" +
//                        "          within such NOTICE file, excluding those notices that do not\n" +
//                        "          pertain to any part of the Derivative Works, in at least one\n" +
//                        "          of the following places: within a NOTICE text file distributed\n" +
//                        "          as part of the Derivative Works; within the Source form or\n" +
//                        "          documentation, if provided along with the Derivative Works; or,\n" +
//                        "          within a display generated by the Derivative Works, if and\n" +
//                        "          wherever such third-party notices normally appear. The contents\n" +
//                        "          of the NOTICE file are for informational purposes only and\n" +
//                        "          do not modify the License. You may add Your own attribution\n" +
//                        "          notices within Derivative Works that You distribute, alongside\n" +
//                        "          or as an addendum to the NOTICE text from the Work, provided\n" +
//                        "          that such additional attribution notices cannot be construed\n" +
//                        "          as modifying the License.\n" +
//                        "\n" +
//                        "      You may add Your own copyright statement to Your modifications and\n" +
//                        "      may provide additional or different license terms and conditions\n" +
//                        "      for use, reproduction, or distribution of Your modifications, or\n" +
//                        "      for any such Derivative Works as a whole, provided Your use,\n" +
//                        "      reproduction, and distribution of the Work otherwise complies with\n" +
//                        "      the conditions stated in this License.\n" +
//                        "\n" +
//                        "   5. Submission of Contributions. Unless You explicitly state otherwise,\n" +
//                        "      any Contribution intentionally submitted for inclusion in the Work\n" +
//                        "      by You to the Licensor shall be under the terms and conditions of\n" +
//                        "      this License, without any additional terms or conditions.\n" +
//                        "      Notwithstanding the above, nothing herein shall supersede or modify\n" +
//                        "      the terms of any separate license agreement you may have executed\n" +
//                        "      with Licensor regarding such Contributions.\n" +
//                        "\n" +
//                        "   6. Trademarks. This License does not grant permission to use the trade\n" +
//                        "      names, trademarks, service marks, or product names of the Licensor,\n" +
//                        "      except as required for reasonable and customary use in describing the\n" +
//                        "      origin of the Work and reproducing the content of the NOTICE file.\n" +
//                        "\n" +
//                        "   7. Disclaimer of Warranty. Unless required by applicable law or\n" +
//                        "      agreed to in writing, Licensor provides the Work (and each\n" +
//                        "      Contributor provides its Contributions) on an \"AS IS\" BASIS,\n" +
//                        "      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or\n" +
//                        "      implied, including, without limitation, any warranties or conditions\n" +
//                        "      of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A\n" +
//                        "      PARTICULAR PURPOSE. You are solely responsible for determining the\n" +
//                        "      appropriateness of using or redistributing the Work and assume any\n" +
//                        "      risks associated with Your exercise of permissions under this License.\n" +
//                        "\n" +
//                        "   8. Limitation of Liability. In no event and under no legal theory,\n" +
//                        "      whether in tort (including negligence), contract, or otherwise,\n" +
//                        "      unless required by applicable law (such as deliberate and grossly\n" +
//                        "      negligent acts) or agreed to in writing, shall any Contributor be\n" +
//                        "      liable to You for damages, including any direct, indirect, special,\n" +
//                        "      incidental, or consequential damages of any character arising as a\n" +
//                        "      result of this License or out of the use or inability to use the\n" +
//                        "      Work (including but not limited to damages for loss of goodwill,\n" +
//                        "      work stoppage, computer failure or malfunction, or any and all\n" +
//                        "      other commercial damages or losses), even if such Contributor\n" +
//                        "      has been advised of the possibility of such damages.\n" +
//                        "\n" +
//                        "   9. Accepting Warranty or Additional Liability. While redistributing\n" +
//                        "      the Work or Derivative Works thereof, You may choose to offer,\n" +
//                        "      and charge a fee for, acceptance of support, warranty, indemnity,\n" +
//                        "      or other liability obligations and/or rights consistent with this\n" +
//                        "      License. However, in accepting such obligations, You may act only\n" +
//                        "      on Your own behalf and on Your sole responsibility, not on behalf\n" +
//                        "      of any other Contributor, and only if You agree to indemnify,\n" +
//                        "      defend, and hold each Contributor harmless for any liability\n" +
//                        "      incurred by, or claims asserted against, such Contributor by reason\n" +
//                        "      of your accepting any such warranty or additional liability.\n" +
//                        "\n" +
//                        "   END OF TERMS AND CONDITIONS\n" +
//                        "\n" +
//                        "   APPENDIX: How to apply the Apache License to your work.\n" +
//                        "\n" +
//                        "      To apply the Apache License to your work, attach the following\n" +
//                        "      boilerplate notice, with the fields enclosed by brackets \"[]\"\n" +
//                        "      replaced with your own identifying information. (Don't include\n" +
//                        "      the brackets!)  The text should be enclosed in the appropriate\n" +
//                        "      comment syntax for the file format. We also recommend that a\n" +
//                        "      file or class name and description of purpose be included on the\n" +
//                        "      same \"printed page\" as the copyright notice for easier\n" +
//                        "      identification within third-party archives.\n" +
//                        "\n" +
//                        "   Copyright [yyyy] [name of copyright owner]\n" +
//                        "\n" +
//                        "   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
//                        "   you may not use this file except in compliance with the License.\n" +
//                        "   You may obtain a copy of the License at\n" +
//                        "\n" +
//                        "       http://www.apache.org/licenses/LICENSE-2.0\n" +
//                        "\n" +
//                        "   Unless required by applicable law or agreed to in writing, software\n" +
//                        "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
//                        "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
//                        "   See the License for the specific language governing permissions and\n" +
//                        "   limitations under the License.\n" +
//                        "\n" +
//                        "Copyright 2008 Google Inc.  All rights reserved.\n" +
//                        "\n" +
//                        "Redistribution and use in source and binary forms, with or without\n" +
//                        "modification, are permitted provided that the following conditions are\n" +
//                        "met:\n" +
//                        "\n" +
//                        "    * Redistributions of source code must retain the above copyright\n" +
//                        "notice, this list of conditions and the following disclaimer.\n" +
//                        "    * Redistributions in binary form must reproduce the above\n" +
//                        "copyright notice, this list of conditions and the following disclaimer\n" +
//                        "in the documentation and/or other materials provided with the\n" +
//                        "distribution.\n" +
//                        "    * Neither the name of Google Inc. nor the names of its\n" +
//                        "contributors may be used to endorse or promote products derived from\n" +
//                        "this software without specific prior written permission.\n" +
//                        "\n" +
//                        "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS\n" +
//                        "\"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT\n" +
//                        "LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR\n" +
//                        "A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT\n" +
//                        "OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n" +
//                        "SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT\n" +
//                        "LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\n" +
//                        "DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\n" +
//                        "THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n" +
//                        "(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\n" +
//                        "OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n" +
//                        "\n" +
//                        "Code generated by the Protocol Buffer compiler is owned by the owner\n" +
//                        "of the input file used when generating it.  This code is not\n" +
//                        "standalone and requires a support library to be linked with it.  This\n" +
//                        "support library is itself covered by the above license.",
//                link = "https://opensource.org/licenses/Zlib"
//            )
        )
    }
}