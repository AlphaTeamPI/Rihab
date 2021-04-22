<?php

namespace App\Entity;

use App\Repository\CommentsRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use TuxOne\ValidationBundle\Validator\Constraints as TuxOneAssert;





/**
 * Comments
 *
 * @ORM\Table(name="comments")
 * @ORM\Entity(repositoryClass=CommentsRepository::class)
 */
class Comments
{

    /**
     * @var integer
     *
     * @ORM\Column(name="idC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;
    /*******************************************************************************/

    /**
     *
     * @ORM\ManyToOne(targetEntity="Posts",inversedBy="comments")
     */
    private $post;

    /**
     * @var string
     * @Assert\NotBlank


     * @ORM\Column(name="contenuC", type="string", length=400, nullable=false)
     */
    private $contenuc;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateC", type="datetime", nullable=true)
     */
    private $datec;

    /**
     * @var integer
     *
     * @ORM\Column(name="reportC", type="integer", nullable=true)
     */
    private $reportc;

    /**
     * @var integer
     *
     * @ORM\Column(name="iduser", type="integer", nullable=true)
     */
    public $iduser;



    /**
     * Get idc
     *
     * @return integer
     */
    public function getIdc()
    {
        return $this->idc;
    }

    /**
     * @return mixed
     */
    public function getPost()
    {
        return $this->post;
    }

    /**
     * @param mixed $post
     */
    public function setPost($post): void
    {
        $this->post = $post;
    }



    /**
     * Set contenuc
     *
     * @param string $contenuc
     *
     * @return Comments
     */
    public function setContenuc($contenuc)
    {
        $this->contenuc = $contenuc;

        return $this;
    }

    /**
     * Get contenuc
     *
     * @return string
     */
    public function getContenuc()
    {
        return $this->contenuc;
    }

    /**
     * Set datec
     *
     * @param \DateTime $datec
     *
     * @return Comments
     */
    public function setDatec($datec)
    {
        $this->datec = $datec;

        return $this;
    }

    /**
     * Get datec
     *
     * @return \DateTime
     */
    public function getDatec()
    {
        return $this->datec;
    }

    /**
     * Set reportc
     *
     * @param integer $reportc
     *
     * @return Comments
     */
    public function setReportc($reportc)
    {
        $this->reportc = $reportc;

        return $this;
    }

    /**
     * Get reportc
     *
     * @return integer
     */
    public function getReportc()
    {
        return $this->reportc;
    }

    /**
     * Set iduser
     *
     * @param integer $iduser
     *
     * @return Comments
     */
    public function setIduser($iduser)
    {
        $this->iduser = $iduser;

        return $this;
    }

    /**
     * Get iduser
     *
     * @return integer
     */
    public function getIduser()
    {
        return $this->iduser;
    }
}
